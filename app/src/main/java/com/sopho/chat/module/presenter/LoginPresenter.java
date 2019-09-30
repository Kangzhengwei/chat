package com.sopho.chat.module.presenter;

import com.sopho.chat.bean.User;
import com.sopho.chat.module.contract.LoginContract;
import com.sopho.chat.rxJava.RxSubscriber;

/**
 * author: kang4
 * Date: 2019/4/25
 * Description:
 */
public class LoginPresenter extends LoginContract.Presenter {

    @Override
    public void login(String phone, String password) {
        mRxManage.addSubscribe(mModel.login(phone, password).subscribeWith(new RxSubscriber<User>() {
            @Override
            protected void _onNext(User user) {
                mView.returnLogin(user);
            }

            @Override
            protected void _onError(String message) {
                mView.returnFail(message);
            }
        }));
    }
}
