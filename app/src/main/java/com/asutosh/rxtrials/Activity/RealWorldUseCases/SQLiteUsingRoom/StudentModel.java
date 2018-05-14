package com.asutosh.rxtrials.Activity.RealWorldUseCases.SQLiteUsingRoom;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Students")
public class StudentModel {
    //Declare student id as a primary key in database will auto generate by Room
    @PrimaryKey(autoGenerate = true)
    public int mId;
    //Declare Column name in table Students
    @ColumnInfo(name = "StudentName")
    public String mName;
    @ColumnInfo (name = "StudentClass")
    public String mStd;
    public int getmId() {
        return mId;
    }
    public void setmId(int mId) {
        this.mId = mId;
    }
    public String getmName() {
        return mName;
    }
    public void setmName(String mName) {
        this.mName = mName;
    }
    public String getmStd() {
        return mStd;
    }
    public void setmStd(String mStd) {
        this.mStd = mStd;
    }
}
