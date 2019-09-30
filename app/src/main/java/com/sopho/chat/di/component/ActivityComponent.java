package com.sopho.chat.di.component;

import android.app.Activity;

import com.sopho.chat.di.moudle.ActivityModule;
import com.sopho.chat.di.scope.ActivityScope;
import com.sopho.chat.module.ui.LoginActivity;
import com.sopho.chat.module.ui.MainActivity;

import dagger.Component;

/**
 * Created by Android on 2018/11/9.
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

    void inject(LoginActivity act);

}
