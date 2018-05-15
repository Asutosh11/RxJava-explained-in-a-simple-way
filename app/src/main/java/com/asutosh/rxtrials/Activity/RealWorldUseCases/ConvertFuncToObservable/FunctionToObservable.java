package com.asutosh.rxtrials.Activity.RealWorldUseCases.ConvertFuncToObservable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.asutosh.rxtrials.R;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FunctionToObservable extends AppCompatActivity {

    /**
     * Here we simply make a function into an Observable to emit the data returned by the function.
     */

    Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        /**
         * Method 1 -
         *
         * THIS IS A VERY BAD AND DANGEROUS WAY OF DOING IT
         * ------------------------------------------------
         *
         * Because what if the 'doSomething()' function takes a bit more time to do the computation and return anything ?
         * It'll block the thread because you're calling doSomething() before passing it to Observable.just().
         */
        Observable<String> observable  = Observable.just(doSomething());



        /**
         * Method 2 -
         *
         * THE CORRECT APPROACH
         * --------------------
         *
         * To get around that problem in 'Method 1' above,
         * here's a trick we use - wrapping the time taking function with 'defer()' operator.
         *
         * Now, the Observable returned won't call doSomething() until you subscribe to it.
         */
        Observable<String> observable1 = Observable.defer(() -> Observable.just(doSomething()));


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

            }

            @Override
            public void onComplete() {
                Log.i("print_items_complete", "over");
            }
        };



        observable1.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);


    }




    public String doSomething(){

        return "return something that takes some time to do the computation like image or video editing";
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }


}

/**
 *
 * Output:
 *
 * I/print_items: return something that takes some time to do the computation like image or video editing
   I/print_items_complete: over
 */