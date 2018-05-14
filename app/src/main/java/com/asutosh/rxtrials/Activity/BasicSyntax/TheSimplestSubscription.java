package com.asutosh.rxtrials.Activity.BasicSyntax;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.asutosh.rxtrials.R;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static io.reactivex.Single.just;

public class TheSimplestSubscription extends AppCompatActivity {

    /**
     * INTRODUCTION
     * ------------
     *
     * In this Activity,
     *
     * I have shown the simplest Observable and Observer and how the code looks like.
     */

    String[] arr;
    private Disposable mDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        /**
         * Observable emits Array items one by one.
         */
        arr = new String[]{"1", "2", "3", "4"};
        Observable<String> database = Observable.fromArray(arr);

        /**
         * Observable catches the emitted data.
         *
         * Every time a new item of the Array is received,
         * we receive it on onNext() method of Observer
         */
        Observer<String> observer = new Observer<String>() {

            @Override
            public void onSubscribe(Disposable d) {
                mDisposable = d;
            }

            @Override
            public void onNext(String s) {
                Log.i("print_items", s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        /**
         * This is the subscription that binds Observable with the Observer
         */
        database.subscribeOn(Schedulers.newThread())          //Observable runs on new background thread.
                .observeOn(AndroidSchedulers.mainThread())    //Observer will run on main UI thread.
                .subscribe(observer);

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
