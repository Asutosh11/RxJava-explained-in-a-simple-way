package com.asutosh.rxtuts.Activity.BasicSyntax;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.asutosh.rxtuts.POJO.Person;
import com.asutosh.rxtuts.R;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class EmitAndReceiveObjectsArray extends AppCompatActivity {

    /**
     * INTRODUCTION
     * ------------
     *
     * In this Activity,
     *
     * The Observable gives an Array of Person object
     *
     * which the Observer receives
     *
     */

    private Disposable mDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Person[] persons = new Person[2];

        Person person = new Person();
        person.setName("Asutosh");
        person.setAge("67");
        person.setGender("male");
        persons[0] = person;

        Person person2 = new Person();
        person2.setName("Jim");
        person2.setAge("91");
        person2.setGender("male");
        persons[1] = person2;

        /**
         * The Observable is emitting Objects
         */
        Observable<Person> observable = Observable.fromArray(persons);

        /**
         * The Observer gets every Object in the Array passed by the Observable.
         */
        Observer<Person> observer = new Observer<Person>() {
            @Override
            public void onSubscribe(Disposable d) {
                mDisposable = d;

            }

            @Override
            public void onNext(Person person) {

                Log.i("print_names", person.getName());
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
 * Asutosh
 * Jim
 */
