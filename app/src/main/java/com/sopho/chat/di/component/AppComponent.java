package com.sopho.chat.di.component;

import android.content.Context;

import com.sopho.chat.di.moudle.ApiModule;
import com.sopho.chat.di.moudle.AppModule;
import com.sopho.chat.di.moudle.XmppModule;
import com.sopho.chat.network.helper.RetrofitHelper;
import com.sopho.chat.xmpp.XmppApiHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author kzw
 * @date 创建时间：2018
 * 描述:AppComponent
 */
@Singleton
@Component(modules = {AppModule.class, ApiModule.class, XmppModule.class})
public interface AppComponent {
    Context getContext();

    RetrofitHelper getRetrofitHelper();

    XmppApiHelper getXmppApiHelper();

}
