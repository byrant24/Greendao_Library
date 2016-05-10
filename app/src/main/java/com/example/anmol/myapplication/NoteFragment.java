package com.example.anmol.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by Amud on 10/05/16.
 */

public class NoteFragment extends Fragment {

    ArrayList<Task> todoList;

    DbHelper db;

    Intent editIntent;
    RecyclerView recyclerView;
    public AddNoteInterface myOnAddNoteInterface;



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }


    public static NoteFragment newInstance()
    {
        NoteFragment fragment = new NoteFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();

    }

    @Override
    public void onResume() {
        super.onResume();
        RecycleAdaptor radap;
        db=new DbHelper(getContext());
        todoList = new ArrayList<Task>();
        todoList = db.getAllTasks();
        radap=new RecycleAdaptor(todoList,getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(radap);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View convertView = inflater.inflate(R.layout.notes_layout, container, false);

        FloatingActionButton btnaddTask=(FloatingActionButton)convertView.findViewById(R.id.addTask);
        recyclerView=(RecyclerView)convertView.findViewById(R.id.recylerid);

        btnaddTask.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                addNewNote();
            }
        });


        return convertView;
    }


    private void addNewNote()
    {
        myOnAddNoteInterface = ((MainActivity) getActivity());
        myOnAddNoteInterface.addNote();
    }

    public interface AddNoteInterface
    {
        public void addNote();
    }
}



