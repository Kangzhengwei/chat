package com.sopho.chat.di.moudle;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.sopho.chat.constant.Constant;
import com.sopho.chat.di.qualifier.AppUrl;
import com.sopho.chat.network.api.Apiservice;
import com.sopho.chat.network.helper.OkHttpHelper;
import com.sopho.chat.network.helper.RetrofitHelper;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    public Retrofit createRetrofit(Retrofit.Builder builder, OkHttpClient client, String url) {
        return builder
                .baseUrl(url)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    public OkHttpClient provideOkHttpClient() {
        return OkHttpHelper.getInstance().getOkHttpClient();
    }

    @Singleton
    @Provides
    public Retrofit.Builder provideRetrofitBuilder() {
        return new Retrofit.Builder();
    }

    @Singleton
    @Provides
    public RetrofitHelper provideRetrofitHelper(Apiservice apiservice) {
        return new RetrofitHelper(apiservice);
    }

    @Singleton
    @Provides
    public Apiservice provideCommunityService(@AppUrl Retrofit retrofit) {
        return retrofit.create(Apiservice.class);
    }

    @Singleton
    @Provides
    @AppUrl
    public Retrofit provideCommunityRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, Constant.BASE_URL);
    }

}
