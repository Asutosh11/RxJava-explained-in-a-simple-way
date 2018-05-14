package com.asutosh.rxtrials.Activity.RealWorldUseCases.SQLiteUsingRoom;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.RxRoom;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.database.Cursor;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings("unchecked")
public class StudentDao_Impl implements StudentDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfStudentModel;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllStudents;

  public StudentDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfStudentModel = new EntityInsertionAdapter<StudentModel>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Students`(`mId`,`StudentName`,`StudentClass`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, StudentModel value) {
        stmt.bindLong(1, value.mId);
        if (value.mName == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.mName);
        }
        if (value.mStd == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.mStd);
        }
      }
    };
    this.__preparedStmtOfDeleteAllStudents = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Students";
        return _query;
      }
    };
  }

  @Override
  public void insertStudent(ArrayList<StudentModel> mStudentModelArrayList) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfStudentModel.insert(mStudentModelArrayList);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAllStudents() {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllStudents.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllStudents.release(_stmt);
    }
  }

  @Override
  public Flowable<List<StudentModel>> getStudents() {
    final String _sql = "SELECT * FROM Students";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return RxRoom.createFlowable(__db, new String[]{"Students"}, new Callable<List<StudentModel>>() {
      @Override
      public List<StudentModel> call() throws Exception {
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfMId = _cursor.getColumnIndexOrThrow("mId");
          final int _cursorIndexOfMName = _cursor.getColumnIndexOrThrow("StudentName");
          final int _cursorIndexOfMStd = _cursor.getColumnIndexOrThrow("StudentClass");
          final List<StudentModel> _result = new ArrayList<StudentModel>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final StudentModel _item;
            _item = new StudentModel();
            _item.mId = _cursor.getInt(_cursorIndexOfMId);
            _item.mName = _cursor.getString(_cursorIndexOfMName);
            _item.mStd = _cursor.getString(_cursorIndexOfMStd);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Maybe<StudentModel> getStudentByName(String aStudentName) {
    final String _sql = "SELECT * FROM Students WHERE StudentName =? ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (aStudentName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, aStudentName);
    }
    return Maybe.fromCallable(new Callable<StudentModel>() {
      @Override
      public StudentModel call() throws Exception {
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfMId = _cursor.getColumnIndexOrThrow("mId");
          final int _cursorIndexOfMName = _cursor.getColumnIndexOrThrow("StudentName");
          final int _cursorIndexOfMStd = _cursor.getColumnIndexOrThrow("StudentClass");
          final com.asutosh.rxtrials.Activity.RealWorldUseCases.SQLiteUsingRoom.StudentModel _result;
          if(_cursor.moveToFirst()) {
            _result = new com.asutosh.rxtrials.Activity.RealWorldUseCases.SQLiteUsingRoom.StudentModel();
            _result.mId = _cursor.getInt(_cursorIndexOfMId);
            _result.mName = _cursor.getString(_cursorIndexOfMName);
            _result.mStd = _cursor.getString(_cursorIndexOfMStd);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }
}
