package com.sopho.chat.di.moudle;

import android.app.Activity;

import com.sopho.chat.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Android on 2018/11/9.
 */

@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityScope
    public Activity provideActivity() {
        return mActivity;
    }
}
