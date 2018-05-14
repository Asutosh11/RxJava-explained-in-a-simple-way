package com.asutosh.rxtrials.Activity.CommonOperators;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.asutosh.rxtrials.R;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RangeAndBuffer extends AppCompatActivity {

    /**
     * This Activity uses 2 operators -
     *
     * 1. Range - > Will emit all the numbers in the range one by one
     *
     * 2. Buffer (of size 3) -> Will take all the data from the observable and will emit all in groups of 3 data
     */

    Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Observable<Integer> observable = Observable.range(1, 10);
        Observable<List<Integer>> observableBuffer = observable.buffer(3);

        Observer observer = new Observer<List<Integer>>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(List<Integer> i) {
                Log.i("print_items", i.toString());

            }

            @Override
            public void onError(Throwable e) {
                Log.i("print_error", e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };


        /**
         * This is the subscription that binds the Observer and the final Observable returned.
         */
        observableBuffer.subscribeOn(Schedulers.io())          //Observable runs on new background thread.
                .observeOn(AndroidSchedulers.mainThread())    //Observer will run on main UI thread.
                .subscribe(observer);


    }

    /**
     * Output
     * ------
     *
     * [1, 2, 3]
     * [4, 5, 6]
     * [7, 8, 9]
     * [10]
     *
     */
}


