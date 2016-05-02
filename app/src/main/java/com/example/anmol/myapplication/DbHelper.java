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
public class DbHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "taskerManager";
    // tasks table name
    private static final String TABLE_TASKS = "tasks";
    // tasks Table Columns names
    private static final String ID = "id";
    private static final String TASKNAME = "taskName";
    private static final String TIME="time";
    private static final String STATUS = "status";

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
            TASKNAME + " TEXT, " + TIME + " TEXT," + STATUS + " INTEGER)";
        db.execSQL(sql);

//        db.close();
    }

    public void addTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TASKNAME, task.getTaskName()); // task name
        values.put(TIME,task.getTime());
        // status of task- can be 0 for not done and 1 for done
        values.put(STATUS, task.getStatus());
        // Inserting Row
        db.insert(TABLE_TASKS, null, values);
//        db.close(); // Closing database connection
    }

    public ArrayList<Task> getAllTasks() {
        ArrayList<Task> taskList = new ArrayList<Task>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_TASKS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Task task = new Task();
                task.setTaskName(cursor.getString(0));
                task.setTime(cursor.getString(1));
                task.setStatus(cursor.getInt(2));
                // Adding contact to list
                taskList.add(task);
            } while (cursor.moveToNext());
        }
        // return task list
        return taskList;
    }

    public void update(String msg){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(STATUS, 1);
        db.update(TABLE_TASKS, values, TASKNAME + " = ?",
                new String[] { msg });

    }

}
