package com.asutosh.rxtrials.Activity.RealWorldUseCases.SQLiteUsingRoom;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

//Declare entities of your room database with version
@Database(entities = StudentModel.class,version = 1)
public abstract class StudentDatabase extends RoomDatabase {
    public abstract StudentDao getStudentDao();
}
