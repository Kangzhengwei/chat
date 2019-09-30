package com.sopho.chat.di.moudle;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * @author
 * @date
 * 描述:App模型
 */
@Module
public class AppModule {
    private Context mContext;

    public AppModule(Context context) {
        this.mContext = context;
    }

    @Provides
    public Context provideContext() {
        return mContext;
    }
}
