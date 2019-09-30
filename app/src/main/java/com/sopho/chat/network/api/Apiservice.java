package com.sopho.chat.network.api;

import com.sopho.chat.bean.Base;
import com.sopho.chat.bean.User;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Apiservice {

    @GET("sophoService/Login")
    Flowable<Base<User>> login(@Query("phoneNumber") String phone, @Query("passWord") String password);
}
