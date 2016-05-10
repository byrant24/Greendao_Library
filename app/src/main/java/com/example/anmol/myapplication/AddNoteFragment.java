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

/**
 * Created by Amud on 10/05/16.
 */


/**
 * Created by Amud on 10/05/16.
 */
public class AddNoteFragment extends Fragment {

    DbHelper db;



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }


    public static AddNoteFragment newInstance( )
    {
        AddNoteFragment fragment = new AddNoteFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View convertView = inflater.inflate(R.layout.add_note_layout, container, false);
        final EditText addNoteView=(EditText)convertView.findViewById(R.id.add_note_view);
        final Button addNoteButton=(Button) convertView.findViewById(R.id.add_note_button);
        db=new DbHelper(getContext());


        addNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String noteText=addNoteView.getText().toString();
                db.addTask(noteText);
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        return convertView;
    }
}

