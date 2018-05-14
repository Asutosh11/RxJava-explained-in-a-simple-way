package com.asutosh.rxtrials.Activity.CommonOperators;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.asutosh.rxtrials.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;

public class Concat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        List<ObservableSource<String>> observableLst = new ArrayList<ObservableSource<String>>();

        ObservableSource<String> observableOne = Observable.just("java", "spring", "hibernate", "android", "rxjava");
        observableLst.add(observableOne);

        ObservableSource<String> observableTwo = Observable.just("language", "di", "orm", "os", "ractive p");
        observableLst.add(observableTwo);

        ObservableSource<String> observableThree = Observable.just("1", "2", "3", "4", "5");
        observableLst.add(observableThree);

        //concat operator takes list of ObservableSource object and returns observable
        //which emits all the items from all source observables
        Observable<String> observableFin = Observable.concat(observableLst);

        observableFin.subscribe(s -> Log.i("print_values", "values after concat operator "+s));


        /**
         * OUTPUT
         * ------
         *
         values after concat operator java
         values after concat operator spring
         values after concat operator hibernate
         values after concat operator android
         values after concat operator rxjava
         values after concat operator language
         values after concat operator di
         values after concat operator orm
         values after concat operator os
         values after concat operator ractive p
         values after concat operator 1
         values after concat operator 2
         values after concat operator 3
         values after concat operator 4
         values after concat operator 5

         */
    }
}
