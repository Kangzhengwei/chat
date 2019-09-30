package com.sopho.chat.base;

import android.app.Application;
import android.content.Context;

import com.sopho.chat.di.component.AppComponent;
import com.sopho.chat.di.component.DaggerAppComponent;
import com.sopho.chat.di.moudle.ApiModule;
import com.sopho.chat.di.moudle.AppModule;
import com.sopho.chat.di.moudle.XmppModule;
import com.sopho.chat.util.AppUtils;
import com.sopho.chat.util.NetworkUtils;
import com.sopho.chat.util.SPUtils;

public class BaseApplication extends Application {

    private AppComponent mAppComponent;
    private static BaseApplication mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        init();
        initComponent();
    }

    private void init() {
        AppUtils.init(this);
        NetworkUtils.startNetService(this);
        SPUtils.init(this, getPackageName() + "_preference", Context.MODE_MULTI_PROCESS);
    }

    /**
     * 初始化网络模块组件
     */
    private void initComponent() {
        mAppComponent = DaggerAppComponent.builder()
                .apiModule(new ApiModule())
                .appModule(new AppModule(this))
                .xmppModule(new XmppModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    public static BaseApplication getInstance() {
        return mContext;
    }
}
