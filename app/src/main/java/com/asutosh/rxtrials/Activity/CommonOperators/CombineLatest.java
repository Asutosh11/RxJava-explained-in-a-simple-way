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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        ObservableSource<String> observableOne = Observable.just("java", "spring", "hibernate", "android", "rxjava");

        ObservableSource<String> observableTwo = Observable.just("language", "di", "orm", "os", "ractive p");

        ObservableSource<String> observableThree = Observable.just("1", "2", "3", "4", "5");

        Observable<String> observableFin = Observable.combineLatest(new Function<Object[], String>(){
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

        observableFin.subscribe(s -> Log.i("print_values", "values after combineLatest operator "+s));


    }
}
