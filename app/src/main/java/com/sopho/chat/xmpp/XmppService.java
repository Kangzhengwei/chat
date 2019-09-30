package com.sopho.chat.xmpp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;


/**
 * author: kang4
 * Date: 2019/4/19
 * Description:
 */
public class XmppService extends Service {

    private AbstractXMPPConnection connection;
    private String s1[];
    private String s2[];

    @Override
    public void onCreate() {
        super.onCreate();
        connection = XmppHelper.getInstance().getXmppConnect();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (connection != null && connection.isConnected()) {
            connection.addAsyncStanzaListener(packet -> {
                if (packet instanceof Presence) {
                    Presence presence = (Presence) packet;
                    s1 = presence.getFrom().toString().toUpperCase().split("@");//发送方
                    s2 = presence.getTo().toString().toUpperCase().split("@");// 接收方
                } else if (packet instanceof Message) {

                }
            }, null);//MessageTypeFilter.HEADLINE
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
