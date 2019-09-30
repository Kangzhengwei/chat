package com.sopho.chat.ObserverPattern;

/**
 * author: kang4
 * Date: 2019/5/29
 * Description:
 */
public class BinaryObserver extends Observer {
    public BinaryObserver(Subject subject){
        this.subject=subject;
        this.subject.attach(this);
    }
    @Override
    public void update() {
        System.out.println( "Binary String: "
                + Integer.toBinaryString( subject.getState() ) );
    }
}
