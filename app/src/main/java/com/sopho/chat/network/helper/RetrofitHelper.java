package com.sopho.chat.network.helper;

import com.sopho.chat.bean.Base;
import com.sopho.chat.bean.User;
import com.sopho.chat.network.api.Apiservice;

import io.reactivex.Flowable;

public class RetrofitHelper {
    private final Apiservice apiservice;

    public RetrofitHelper(Apiservice apiservice) {
        this.apiservice = apiservice;
    }

    public Flowable<Base<User>> login(String phone, String password) {
        return apiservice.login(phone, password);
    }
}
