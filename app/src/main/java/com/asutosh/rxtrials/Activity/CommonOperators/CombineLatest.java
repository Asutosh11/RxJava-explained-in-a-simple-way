package com.asutosh.rxtrials.Activity.CommonOperators;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.asutosh.rxtrials.R;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

public class CombineLatest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /**
         * Here we have 3 Observables.
         *
         * For every item in 'observableThree' we are adding the last item of 'observableOne' & 'observableTwo'
         * We emit that combined stream to the Observer. It emits all items one by one.
         * Se the output.
         */

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        ObservableSource<String> observableOne = Observable.just("alphabet", "barcode", "chrome", "diagram", "edge");

        ObservableSource<String> observableTwo = Observable.just("antilope", "bull", "cat", "dog", "elephant");

        ObservableSource<String> observableThree = Observable.just("1", "2", "3", "4", "5");

        Observable<String> observableCombine = Observable.combineLatest(new Function<Object[], String>(){
                                                                        @Override
                                                                        public String apply(Object[] t) throws Exception {
                                                                            String finObj = "";
                                                                            for(Object ob : t){
                                                                                finObj = finObj + ob+ " ";
                                                                            }
                                                                            return finObj.trim();
                                                                        }},
                1,
                observableOne, observableTwo, observableThree);

        observableCombine.subscribe(s -> Log.i("print_values", s));


    }
}

/**
 * Output in Logcat:
 *
 edge elephant 1
 edge elephant 2
 edge elephant 3
 edge elephant 4
 edge elephant 5

 */
