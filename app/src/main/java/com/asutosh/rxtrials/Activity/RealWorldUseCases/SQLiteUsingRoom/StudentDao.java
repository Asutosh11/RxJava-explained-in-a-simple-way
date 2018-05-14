package com.asutosh.rxtrials.Activity.RealWorldUseCases.SQLiteUsingRoom;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;

@Dao
public interface StudentDao {
    //Flowable or observable is used to emits Student model types of data and it emits whenever database is updated
    @Query("SELECT * FROM Students")
    Flowable<List<StudentModel>> getStudents();
    //This Maybe class is used to emit only a single row data
    @Query("SELECT * FROM Students WHERE StudentName =:aStudentName ")
    Maybe<StudentModel> getStudentByName(String aStudentName);
    //Insert the parameters in table like list of students
    @Insert
    void insertStudent(ArrayList<StudentModel> mStudentModelArrayList);
    //This Query will delete all the students from table Students
    @Query("DELETE FROM Students")
    void deleteAllStudents();
}