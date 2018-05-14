package com.asutosh.rxtrials.Networking.Interface;

import com.asutosh.rxtrials.Networking.RetrofitPOJO.Headlines;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIsInterface {

    @GET("top-headlines")
    Observable<Headlines> getHeadlines(@Query("country") String country, @Query("apiKey") String apiKey);
}


