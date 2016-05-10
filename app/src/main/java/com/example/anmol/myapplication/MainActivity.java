package com.example.anmol.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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

public class MainActivity extends AppCompatActivity  implements RecycleAdaptor.EditNoteInterface, NoteFragment.AddNoteInterface{
    ArrayList<Task> todoList;
    FloatingActionButton btnaddTask;
    DbHelper db;
    RecyclerView recyclerView;
    Intent editIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        NoteFragment noteFragment = NoteFragment.newInstance();
        fragmentTransaction.replace(R.id.main_frag, noteFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void editNote(int id) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        EditNoteFragment editNoteFragment = EditNoteFragment.newInstance(id);
        fragmentTransaction.replace(R.id.main_frag, editNoteFragment).addToBackStack(getClass().getName());
        fragmentTransaction.commit();
    }

    @Override
    public void addNote() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        AddNoteFragment addNoteFragment = AddNoteFragment.newInstance();
        fragmentTransaction.replace(R.id.main_frag, addNoteFragment).addToBackStack(getClass().getName());
        fragmentTransaction.commit();
    }

}


