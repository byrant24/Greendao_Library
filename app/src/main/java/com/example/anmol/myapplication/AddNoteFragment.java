package com.example.anmol.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Anmol on 10/05/16.
 */

public class AddNoteFragment extends Fragment {

    DbHelper db;
    String email;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }


    public static AddNoteFragment newInstance(String email) {
        AddNoteFragment fragment = new AddNoteFragment();
        Bundle bundle = new Bundle();
        bundle.putString("email", email);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        email = getArguments().getString("email");

    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View convertView = inflater.inflate(R.layout.add_note_layout, container, false);
        final EditText addNoteView = (EditText) convertView.findViewById(R.id.add_note_view);
        final Button addNoteButton = (Button) convertView.findViewById(R.id.add_note_button);
        db = new DbHelper(getContext());


        addNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String noteText = addNoteView.getText().toString();
                if (noteText.isEmpty())
                    Toast.makeText(getActivity(), "type something", Toast.LENGTH_SHORT).show();
                else {
                    db.addTask(noteText, email);
                    getActivity().getSupportFragmentManager().popBackStack();
                }
            }
        });

        return convertView;
    }
}

