package com.asutosh.rxtrials.Networking;

import com.asutosh.rxtrials.Networking.Interface.APIsInterface;
import com.asutosh.rxtrials.Networking.Helper.UrlHelper;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitBuilder {

    private APIsInterface mAPIsInterface;

    public APIsInterface RetrofitBuilder() {
        
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(UrlHelper.baseURL)
                .build();

        mAPIsInterface = retrofit.create(APIsInterface.class);

        return mAPIsInterface;
    }
}
