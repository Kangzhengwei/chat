package com.sopho.chat.xmpp;

import com.sopho.chat.constant.Constant;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;

import java.io.IOException;
import java.net.InetAddress;

public class XmppHelper {

    private volatile static XmppHelper instance;
    private AbstractXMPPConnection connection;

    public static XmppHelper getInstance() {
        if (instance == null) {
            synchronized (XmppHelper.class) {
                if (instance == null) {
                    instance = new XmppHelper();
                }
            }
        }
        return instance;
    }

    public AbstractXMPPConnection getXmppConnect() {
        return connection;
    }

    private XmppHelper() {
        try {
            SmackConfiguration.DEBUG = true;
            XMPPTCPConnectionConfiguration config = XMPPTCPConnectionConfiguration.builder()
                    .setHostAddress(InetAddress.getByName(Constant.HOST)) //设置openfire主机IP
                    .setXmppDomain(Constant.HOST) //设置openfire服务器名称
                    .setPort(Constant.PORT)   //设置端口号：默认5222
                    .setSecurityMode(ConnectionConfiguration.SecurityMode.disabled)  //禁用SSL连接
                    .setSendPresence(false)  //设置离线状态
                    .setCompressionEnabled(true)  //设置开启压缩，可以节省流量
                    .build();
            connection = new XMPPTCPConnection(config);
        } catch (IOException xe) {
            xe.printStackTrace();
        }
    }
}
