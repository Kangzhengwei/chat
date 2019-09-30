package com.sopho.chat.di.component;

import android.app.Activity;

import com.sopho.chat.di.moudle.FragmentModule;
import com.sopho.chat.di.scope.FragmentScope;

import dagger.Component;

/**
 * Created by Android on 2018/11/12.
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();

}
