package com.asutosh.rxtuts.Activity.CommonOperators;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.asutosh.rxtuts.R;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class Map extends AppCompatActivity {

    private Disposable mDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        /**
         * Here for every item in the Observable, we are adding some extra String.
         * See the output below.
         */

        ArrayList<String> list = new ArrayList<>();
        list.add("blue");
        list.add("red");
        list.add("pink");
        list.add("violet");
        list.add("green");
        list.add("black");
        list.add("white");


        Observable<String> observable = Observable.fromIterable(list).map(new Function<String, String>() {
            @Override
            public String apply(String s) throws Exception {
                return s+" mapped String";
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

/**
 * Output in Logcat:
 *
 blue mapped String
 red mapped String
 pink mapped String
 violet mapped String
 green mapped String
 black mapped String
 white mapped String
 *
 */