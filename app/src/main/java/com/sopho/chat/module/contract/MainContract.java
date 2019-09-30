package com.sopho.chat.module.contract;

import com.sopho.chat.base.BaseModel;
import com.sopho.chat.base.BasePresenter;
import com.sopho.chat.base.BaseView;

public interface MainContract {
    interface Model extends BaseModel {

    }

    interface View extends BaseView {

    }

    abstract class Presenter extends BasePresenter<View, Model> {

    }
}
