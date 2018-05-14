package com.asutosh.rxtrials.Activity.RealWorldUseCases.RetrofitApiCall;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.asutosh.rxtrials.R;
import com.asutosh.rxtrials.Networking.Interface.APIsInterface;
import com.asutosh.rxtrials.Networking.RetrofitBuilder;
import com.asutosh.rxtrials.Networking.RetrofitPOJO.Headlines;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RetrofitInRxJava extends AppCompatActivity {


    /**
     * INTRODUCTION
     * ------------
     *
     * This Activity implements a simple Retrofit Get call.
     * Retrofit has 2 ways of implementing itself, the normal usual way and the other is the RxJava way
     *
     * Here we are making Retrofit API call in the RxJava way
     */

    ///////////////////////////////////////////////////////////////////////////////////////////////


    /**
     * For making the Retrofit call
     */
    private APIsInterface mAPIsInterface;

    /**
     * Collects the subscription to unsubscribe later
     */
    private Disposable mDisposable;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        /**
         * Use Retrofit to make the API calls
         * and attach an observer to observe the API call
         */
        RetrofitBuilder mRetrofitBuilder = new RetrofitBuilder();

        /**
         * 1.
         * This is the API we are calling
         * Here the API call is the 'Observable'
         */
        Observable<Headlines> observable =
                mRetrofitBuilder.RetrofitBuilder().getHeadlines("us", "227c9e8935bc4373b83e0908795e5696");

        /**
         * 2.
         * The 'observer' to observe the API call
         */
        Observer<Headlines> observer = new Observer<Headlines>() {
            @Override
            public void onSubscribe(Disposable d) {
                mDisposable = d;

            }

            @Override
            public void onNext(Headlines headlinesBean) {
                Log.d("RxOnNext", ""+headlinesBean.getStatus() + "  " + headlinesBean.getTotalResults());
                Toast.makeText(getApplicationContext(),
                        ""+headlinesBean.getStatus() + "  " + headlinesBean.getTotalResults(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onError(Throwable e) {
                Log.d("RxError", ""+e+"");
                Toast.makeText(getApplicationContext(), e+"", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onComplete() {
                Log.d("RxComplete", "DONE/Completed");
                Toast.makeText(getApplicationContext(), "Done/Completed", Toast.LENGTH_LONG).show();
            }
        };


        /**
         * 3.
         * The Subscription that connects Observable and Observer
         *
         *
         * 'subscribeOn' means in which thrad you should register the Observable
         *
         * 'observeOn' means in which thread the Observer should observe the Observable
         */

        observable
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(observer);


        /**
         * Like the subscriber shown above, one good way is,
         *
         * 1. if you are calling an API in the Observable,
         *    register the Observable on a background thread for the heavy work
         *
         * 2. and for the data you got on the observer, if you have to update in on main thread,
         *    register the observer on the Main thread
         *
         */


    }


    @Override
    protected void onStop() {
        super.onStop();

        /**
         * 4. Unsubscribe the Subscription.
         *
         *    Its important to do this to avoid memory leaks when stopping an Activity.
         *    Basically on unsubscribing you are freeing up all the resources for garbage collection.
         */
        mDisposable.dispose();
    }

}
