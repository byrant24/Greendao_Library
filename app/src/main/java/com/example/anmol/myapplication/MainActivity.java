package com.example.anmol.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity implements OnClickListener{
    Button btnCalendar, btnTimePicker,butnSave;
    EditText txtDate, txtTime,txtNote;
    String note;

    AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    //private TimePicker alarmTimePicker;
    private static MainActivity inst;
    //private TextView alarmTextView;
    // Variable for storing current date and time
    private int mYear, mMonth, mDay, mHour, mMinute;

    public static MainActivity instance() {
        return inst;
    }

    @Override
    public void onStart() {
        super.onStart();
        inst = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCalendar = (Button) findViewById(R.id.btnCalendar);
        btnTimePicker = (Button) findViewById(R.id.btnTimePicker);
        txtDate = (EditText) findViewById(R.id.txtDate);
        txtTime = (EditText) findViewById(R.id.txtTime);
        txtNote =  (EditText) findViewById(R.id.note);
        butnSave=(Button) findViewById(R.id.btnSave);
        btnCalendar.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);
        butnSave.setOnClickListener(this);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
    }

    public void onClick(View v) {

        if (v == btnCalendar) {

            // Process to get Current Date
            final Calendar c = Calendar.getInstance();
            int Year = c.get(Calendar.YEAR);
            int Month = c.get(Calendar.MONTH);
            int Day = c.get(Calendar.DAY_OF_MONTH);

            // Launch Date Picker Dialog
            DatePickerDialog dpd = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            // Display Selected date in textbox
                            mYear = year;
                            mMonth = monthOfYear;
                            mDay = dayOfMonth;

                            txtDate.setText(dayOfMonth + "-"
                                    + (monthOfYear + 1) + "-" + year);

                        }
                    }, Year, Month, Day);

            dpd.show();
        }
        if (v == btnTimePicker) {

            // Process to get Current Time
            final Calendar c = Calendar.getInstance();
            int Hour = c.get(Calendar.HOUR_OF_DAY);
            int Minute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog tpd = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            mHour=hourOfDay;
                            mMinute=minute;
                            // Display Selected time in textbox
                            txtTime.setText(hourOfDay + ":" + minute);


                        }
                    }, Hour, Minute, false);
            tpd.show();
        }
        if(v == butnSave)
        {
            //CONVERTING THE TEXT IN TO STRING...
            note = txtNote.getText().toString();
            Intent myIntent = new Intent(this, AlarmReceiver.class);
            myIntent.putExtra("message", note);
            pendingIntent = PendingIntent.getBroadcast(this, 0, myIntent, 0);

            Calendar calendar = Calendar.getInstance();
            calendar.set(mYear,mMonth,mDay,mHour,mMinute);

            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        }
    }

}

