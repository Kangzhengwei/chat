package com.sopho.chat.rxJava;

import com.sopho.chat.network.helper.ApiException;
import com.sopho.chat.util.LogUtils;

import java.net.SocketTimeoutException;

import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.HttpException;


public abstract class RxSubscriber<T> extends ResourceSubscriber<T> {

    @Override
    public void onComplete() {
    }

    @Override
    public void onStart() {
        super.onStart();
    }


    @Override
    public void onNext(T t) {
        _onNext(t);
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof ApiException) {
            _onError(e.toString());
        } else if (e instanceof Exception) {
            _onError(e.toString());
        } else if (e instanceof SocketTimeoutException) {
            _onError("服务器响应超时ヽ(≧Д≦)ノ");
        } else if (e instanceof HttpException) {
            _onError("数据加载失败ヽ(≧Д≦)ノ");
        } else {
            _onError("未知错误ヽ(≧Д≦)ノ");
            LogUtils.e("MYERROR:" + e.toString());
        }
    }

    protected abstract void _onNext(T t);

    protected abstract void _onError(String message);

}
