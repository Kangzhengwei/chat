package com.sopho.chat.ObserverPattern;

/**
 * author: kang4
 * Date: 2019/5/29
 * Description:
 */
public class HexaObserver extends Observer{
    public HexaObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }
    @Override
    public void update() {
        System.out.println( "Hex String: "
                + Integer.toHexString( subject.getState() ).toUpperCase() );
    }
}
