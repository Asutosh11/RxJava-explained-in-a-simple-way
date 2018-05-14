package com.asutosh.rxtrials.Activity.CommonOperators;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.asutosh.rxtrials.R;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;


public class Filter extends AppCompatActivity {

    private Disposable mDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ArrayList<String> list = new ArrayList<>();
        list.add("blue");
        list.add("red");
        list.add("pink");
        list.add("violet");
        list.add("green");
        list.add("black");
        list.add("white");

        Observable<String> observable = Observable.fromIterable(list)

                                                  .filter(new Predicate<String>() {
                                                      @Override public boolean test(String v) {
                                                       return v.contains("e");
                                                    }
                                                  });


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
        observable.subscribeOn(Schedulers.newThread())          //Observable runs on new background thread.
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
