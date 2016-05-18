package com.example.anmol.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
 * Created by Anmol on 10/05/16.
 */

public class NoteFragment extends Fragment {

    ArrayList<Task> todoList;

    DbHelper db;

    RecyclerView recyclerView;
    public AddNoteInterface myOnAddNoteInterface;
    String emailId;



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
        SharedPreferences m_sharedPref;
        m_sharedPref = getActivity().getSharedPreferences(
                "com.example.anmol.myapplication", Context.MODE_PRIVATE);

        emailId = m_sharedPref.getString("email_id", "");

    }

    @Override
    public void onResume() {
        super.onResume();
        RecycleAdaptor radap;



        db=new DbHelper(getContext());
        todoList = new ArrayList<Task>();
        todoList = db.getAllTasks(emailId);
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
                addNewNote(emailId);
            }
        });


        return convertView;
    }


    private void addNewNote(String email)
    {
        myOnAddNoteInterface = ((MainActivity) getActivity());
        myOnAddNoteInterface.addNote(email);
    }

    public interface AddNoteInterface
    {
        public void addNote(String email);
    }
}



