package com.example.anmol.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Calendar;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnClickListener{
    ArrayList<Task> todoList;
    FloatingActionButton btnaddTask;
    DbHelper db;
    RecyclerView recyclerView;
    Intent editIntent;

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


        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Task task = todoList.get(position);
                //Toast.makeText(getApplicationContext(), task.getTaskName(), Toast.LENGTH_SHORT).show();
//                editIntent=new Intent(this,EditTask.class);
//                editIntent.putExtra("message", task.getTaskName());
//                startActivity(editIntent);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }

    void startActivity() {
        Intent mintent=new Intent(this,AddTaskActivity.class);
        startActivity(mintent);
    }

    @Override
    public void onClick(View v) {

    }

    //Adding RecyclerView Item Click Listener
    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }
    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{
        private GestureDetector gestureDetector;
        private MainActivity.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final MainActivity.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

}


