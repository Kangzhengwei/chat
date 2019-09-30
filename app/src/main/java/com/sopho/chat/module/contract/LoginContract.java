package com.sopho.chat.module.contract;

import com.sopho.chat.base.BaseModel;
import com.sopho.chat.base.BasePresenter;
import com.sopho.chat.base.BaseView;
import com.sopho.chat.bean.User;

import io.reactivex.Flowable;

/**
 * author: kang4
 * Date: 2019/4/25
 * Description:
 */
public interface LoginContract {
    interface Model extends BaseModel {
        Flowable<User> login(String phone, String password);
    }

    interface View extends BaseView {
        void returnLogin(User result);
    }

    abstract class Presenter extends BasePresenter<View, Model> {

        public abstract void login(String phone, String password);
    }
}
