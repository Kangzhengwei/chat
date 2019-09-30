package com.sopho.chat.di.moudle;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.sopho.chat.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * @author kzw 作者
 * @date 创建时间：2018/11/12
 * 描述:Fragment模型
 */
@Module
public class FragmentModule {

    private Fragment mFragment;

    public FragmentModule(Fragment fragment) {
        this.mFragment = fragment;
    }

    @Provides
    @FragmentScope
    public Activity provideActivity() {
        return mFragment.getActivity();
    }
}
