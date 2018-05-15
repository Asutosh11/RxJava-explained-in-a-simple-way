package com.asutosh.rxtrials.Activity.RealWorldUseCases.SQLiteUsingRoom;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.asutosh.rxtrials.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RoomSqliteActivity extends AppCompatActivity {

    private static final String DB_NAME = "database-name2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Access Room database in activity to get data access object
        StudentDatabase mStudentDatabase = Room.databaseBuilder(getBaseContext(),
                StudentDatabase.class, DB_NAME).build();


        ArrayList<StudentModel> modelArrayList = new ArrayList<>();
        //Saving some records
        for (int i=0;i<10;i++){
            StudentModel mStudentModel = new StudentModel();
            mStudentModel.setmName("name "+i);
            mStudentModel.setmStd("class "+i);
            modelArrayList.add(mStudentModel);
        }

        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {

                //inserting records
                mStudentDatabase.getStudentDao().insertStudent(modelArrayList);

            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable e) {

            }
        });

        //getting flowable to subscribe consumer that will access the data from Room database.
        mStudentDatabase.getStudentDao().getStudents().subscribe(new Consumer<List<StudentModel>>() {
            @Override
            public void accept(@NonNull List<StudentModel> studentModels) throws Exception {
                handleResponse(studentModels);
            }
        });
    }
    private void handleResponse(List<StudentModel> studentModels){
        Log.i("student_size :",studentModels.size()+"");
        for (int i=0;i<studentModels.size();i++){
            Log.i("student_name :",studentModels.get(i).getmName());
        }
    }
}

/**
 * Output:
 *
 * I/student_size :: 0
   I/student_size :: 10
   I/student_name :: name 0
   name 1
   name 2
   name 3
   name 4
   name 5
   name 6
   name 7
   name 8
   name 9

 */
