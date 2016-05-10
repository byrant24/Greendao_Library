package com.example.anmol.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anmol on 01-05-2016.
 */
public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "taskerManager";
    // tasks table name
    private static final String TABLE_TASKS = "tasks";
    // tasks Table Columns names
    private static final String ID = "id";
    private static final String TASKNAME = "taskName";
    private static final String TIME = "time";
    private static final String EMAIL = "email_id";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);

        // Create tables again
//        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_TASKS + " ( " +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT,  " + TASKNAME + " TEXT, " +  EMAIL + " TEXT, "+ TIME + " DEFAULT CURRENT_TIMESTAMP )";
        db.execSQL(sql);

//        db.close();
    }

    public void addTask(String note,String email) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TASKNAME, note); // task name
        values.put(EMAIL, email); // task email
        db.insert(TABLE_TASKS, null, values);
    }

    public ArrayList<Task> getAllTasks(String emailId) {
        ArrayList<Task> taskList = new ArrayList<Task>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_TASKS + " where " + EMAIL + " = ?";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{emailId});

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Task task = new Task();
                task.setId(cursor.getInt(cursor.getColumnIndex(ID)));
                task.setTaskName(cursor.getString(cursor.getColumnIndex(TASKNAME)));
                task.setTime(cursor.getString(cursor.getColumnIndex(TIME)));
                task.setEmail(cursor.getString(cursor.getColumnIndex(EMAIL)));
                taskList.add(task);
            } while (cursor.moveToNext());
        }
        // return task list
        return taskList;
    }

    public void update(int id, String msg) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TASKNAME, msg);
        db.update(TABLE_TASKS, values, ID + " = ?",
                new String[]{String.valueOf(id)});

    }

    public void delete(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_TASKS, ID + " = ?",
                new String[]{String.valueOf(id)});

    }

}
