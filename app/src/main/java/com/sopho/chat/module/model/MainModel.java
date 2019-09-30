package com.sopho.chat.module.model;

import com.sopho.chat.module.contract.MainContract;
import com.sopho.chat.network.helper.RetrofitHelper;

import javax.inject.Inject;

public class MainModel implements MainContract.Model {

    RetrofitHelper retrofitHelper;

    @Inject
    public MainModel(RetrofitHelper retrofitHelper){
        this.retrofitHelper=retrofitHelper;
    }

}
