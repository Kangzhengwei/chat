package com.sopho.chat.module.model;

import com.sopho.chat.bean.User;
import com.sopho.chat.module.contract.LoginContract;
import com.sopho.chat.network.helper.RetrofitHelper;
import com.sopho.chat.rxJava.RxHelper;
import com.sopho.chat.rxJava.RxSchedulers;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * author: kang4
 * Date: 2019/4/25
 * Description:
 */
public class LoginModel implements LoginContract.Model {
    RetrofitHelper retrofitHelper;

    @Inject
    public LoginModel(RetrofitHelper retrofitHelper) {
        this.retrofitHelper = retrofitHelper;
    }

    @Override
    public Flowable<User> login(String phone, String password) {
        return retrofitHelper
                .login(phone, password)
                .compose(RxHelper.handleBaseResult())
                .compose(RxSchedulers.io_main());
    }
}
