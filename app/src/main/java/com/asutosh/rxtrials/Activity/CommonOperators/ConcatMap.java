package com.asutosh.rxtrials.Activity.CommonOperators;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.asutosh.rxtrials.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class ConcatMap extends AppCompatActivity {

    /**
     * INTRODUCTION
     * ------------
     *
     * In this Activity,
     *
     * we have
     *
     * 1. observableOne: Gives String data one by one to observableTwo
     * 2. observableTwo process every item given by observableOne
     * 3. observableTwo makes another Observable to give to the Observer, the type of Observervable it makes is of String type by
     *    merging every item from observableOne with a static string 'after thru concatMap'.
     */

    private Disposable disposable;
    String TAG = "print_data_concatMap";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        /**
         * This is one Observable that is giving 'data - 1'
         */
        Observable<String> observableOne = Observable.just("alphabet", "barcode", "chrome", "diagram", "edge");

        /**
         * This second observable which takes each and every time given by the above first observable.
         * It changes every item given by first observable and after processing the whole data,
         * it returns the whole data as an observable
         *
         * which can be further observed by an observer.
         */
        Observable<String> observableTwo = observableOne.concatMap(new Function<String, ObservableSource<String>>(){
            @Override
            public ObservableSource<String> apply(String t) throws Exception {
                return Observable.just(t+" after thru concatMap").delay(200, TimeUnit.MILLISECONDS);

            }
        });


        /**
         * This Observer is observing the final Observable returned by 'Observable<String> observableTwo' above
         */
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(String s) {
                Log.i("print_items", s);

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
        observableTwo.subscribeOn(Schedulers.io())          //Observable runs on a thread taken from thread pool.
                .observeOn(AndroidSchedulers.mainThread())    //Observer will run on main UI thread.
                .subscribe(observer);




    }

        @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }

}

/**
 * Output in Logcat:
 *
 alphabet after thru concatMap
 barcode after thru concatMap
 chrome after thru concatMap
 diagram after thru concatMap
 edge after thru concatMap

 */


/**
 * PLEASE NOTE THAT THERE IS A SMALL DIFFERENCE BETWEEN 'FLATMAP' AND 'CONCATMAP'
 * ------------------------------------------------------------------------------
 *
 * Lets suppose we are doing the processing in multiple threads.
 *
 * ITEM 1 in thread 1.
 * ITEM 2 in thread 2.
 * ITEM 3 in thread 3.
 *
 * FlatMap will return the ITEM that has finished processing, doesn't matter if items prior to it
 * in the queue have finished processing or not.
 *
 * ConcatMap will wait for ITEM 1 to be finished first, doesn't matter if other have finished processing or not.
 * ConcatMap will emit items sequencially.
 */
