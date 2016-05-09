package com.example.anmol.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Anmol on 10-05-2016.
 */
public class EditTask extends AppCompatActivity implements View.OnClickListener {

    EditText editTask;
    Button tasksave;
    Button taskDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.taskview);
//        String msg = editintent.getStringExtra("message");
//        editTask =(EditText) findViewById(R.id.edittaskview);
//        editTask.setText();


    }

    @Override
    public void onClick(View v) {

    }
}
