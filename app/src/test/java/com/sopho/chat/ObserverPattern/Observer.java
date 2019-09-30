package com.sopho.chat.ObserverPattern;

/**
 * author: kang4
 * Date: 2019/5/29
 * Description:
 */
public abstract class Observer {
    protected Subject subject;
    public abstract void update();
}
