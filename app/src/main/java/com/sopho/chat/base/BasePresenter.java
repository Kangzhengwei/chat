package com.sopho.chat.base;

import android.content.Context;

import com.sopho.chat.rxJava.RxManager;

import javax.inject.Inject;


public abstract class BasePresenter<T, E> {

    @Inject
    protected E mModel;
    public Context mContext;
    public T mView;
    public RxManager mRxManage = new RxManager();

    public void setVM(T v, E m) {
        this.mView = v;
        this.mModel = m;
        this.onStart();
    }

    public void onStart() {
    }

    public void onDestroy() {
        mRxManage.unSubscribe();
    }
}
