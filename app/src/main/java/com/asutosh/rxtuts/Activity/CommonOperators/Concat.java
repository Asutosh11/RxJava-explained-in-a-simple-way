package com.asutosh.rxtuts.Activity.CommonOperators;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.asutosh.rxtuts.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;

public class Concat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /**
         * Here we have 3 observables.
         *
         * We concat the data emitted by all the Observables into one stream.
         * We emit that combined stream to the Observer. It emits all items one by one.
         * Se the output.
         */

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        List<ObservableSource<String>> observableLst = new ArrayList<ObservableSource<String>>();

        ObservableSource<String> observableOne = Observable.just("alphabet", "barcode", "chrome", "diagram", "edge");
        observableLst.add(observableOne);

        ObservableSource<String> observableTwo = Observable.just("aa", "bb", "cc", "dd", "ee");
        observableLst.add(observableTwo);

        ObservableSource<String> observableThree = Observable.just("1", "2", "3", "4", "5");
        observableLst.add(observableThree);

        Observable<String> observableConcat = Observable.concat(observableLst);
        observableConcat.subscribe(s -> Log.i("print_values", s));


        /**
         * Output in Logcat
         * ------

         alphabet
         barcode
         chrome
         diagram
         edge
         aa
         bb
         cc
         dd
         ee
         1
         2
         3
         4
         5

         */
    }
}
