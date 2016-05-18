package com.example.anmol.myapplication;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Anmol on 10/05/16.
 */
public class EditNoteFragment extends Fragment {

    DbHelper db;
    int id;
    String note;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }


    public static EditNoteFragment newInstance(int id, String note) {
        EditNoteFragment fragment = new EditNoteFragment();
        Bundle args = new Bundle();
        args.putInt("id",id);
        args.putString("note", note);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        id = bundle.getInt("id");
        note = bundle.getString("note");


    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View convertView = inflater.inflate(R.layout.edit_note, container, false);
        final EditText editNoteView = (EditText) convertView.findViewById(R.id.edit_note_view);
        final Button editNoteButton = (Button) convertView.findViewById(R.id.edit_note_button);
        db = new DbHelper(getContext());

        editNoteView.setText(note);
        editNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String noteText = editNoteView.getText().toString();
                db.update(id, noteText);
                getActivity().getSupportFragmentManager().popBackStack();


            }
        });

        return convertView;
    }
}
