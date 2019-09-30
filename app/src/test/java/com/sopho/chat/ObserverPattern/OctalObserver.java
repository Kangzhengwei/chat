package com.sopho.chat.ObserverPattern;

/**
 * author: kang4
 * Date: 2019/5/29
 * Description:
 */
public class OctalObserver extends Observer {

    public OctalObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }
    @Override
    public void update() {
        System.out.println( "Octal String: "
                + Integer.toOctalString( subject.getState() ) );
    }
}
