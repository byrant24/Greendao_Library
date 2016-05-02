package com.example.anmol.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Calendar;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity implements OnClickListener{
    ArrayList<Task> todoList;
    FloatingActionButton btnaddTask;
    DbHelper db;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnaddTask=(FloatingActionButton)findViewById(R.id.addTask);
        recyclerView=(RecyclerView)findViewById(R.id.recylerid);

        btnaddTask.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity();
            }
        });
        RecycleAdaptor radap;
        db=new DbHelper(this);
        todoList = new ArrayList<Task>();
        todoList = db.getAllTasks();
        radap=new RecycleAdaptor(todoList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(radap);

    }

    void startActivity() {
        Intent mintent=new Intent(this,AddTaskActivity.class);
        startActivity(mintent);
    }

    @Override
    public void onClick(View v) {

    }
}

