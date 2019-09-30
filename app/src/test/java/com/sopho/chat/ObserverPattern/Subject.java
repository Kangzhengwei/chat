package com.sopho.chat.ObserverPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * author: kang4
 * Date: 2019/5/29
 * Description:
 */
public class Subject {
    private List<Observer> observers=new ArrayList<>();

    private int state;

    public int getState(){
        return state;
    }

    public void setState(int state){
        this.state=state;
        notifyAllObservers();
    }

    public void attach(Observer observer){
        observers.add(observer);
    }

    public void notifyAllObservers(){
        for (Observer observer:observers){
            observer.update();
        }
    }
}
