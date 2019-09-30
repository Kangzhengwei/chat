package com.sopho.chat.di.moudle;


import com.sopho.chat.xmpp.XmppApi;
import com.sopho.chat.xmpp.XmppApiHelper;
import com.sopho.chat.xmpp.XmppHelper;

import org.jivesoftware.smack.AbstractXMPPConnection;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class XmppModule {

    @Singleton
    @Provides
    public AbstractXMPPConnection provideXmppConnct() {
        return XmppHelper.getInstance().getXmppConnect();
    }

    @Singleton
    @Provides
    public XmppApi provideXmppApi(AbstractXMPPConnection connection) {
        return new XmppApi(connection);
    }

    @Singleton
    @Provides
    public XmppApiHelper provideXmppApiHelper(XmppApi xmppApi) {
        return new XmppApiHelper(xmppApi);
    }


}
