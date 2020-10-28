package com.example.classthree;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class SecondActivity extends AppCompatActivity {

    private TextView textView;
    private Button dateButton,timeButton;
    private TextView dateText,timeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setTitle("Second Activity");
        textView= findViewById(R.id.textId);
        dateButton= findViewById(R.id.dateButtonId);
        timeButton= findViewById(R.id.timeButtonId);
        dateText= findViewById(R.id.dateTextId);
        timeText= findViewById(R.id.timeTextId);

        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePicker timePicker= new TimePicker(SecondActivity.this);
                int hour= timePicker.getHour();
                int min= timePicker.getMinute();

                TimePickerDialog dialog= new TimePickerDialog(SecondActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        timeText.setText("Time: "+hourOfDay+":"+minute);
                    }
                },hour,min,false);
                dialog.show();
            }
        });


        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePicker datePicker= new DatePicker(SecondActivity.this);
                int day= datePicker.getDayOfMonth();
                int month= datePicker.getMonth();
                int year= datePicker.getYear();

                DatePickerDialog dialog= new DatePickerDialog(SecondActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        int a= month+1;
                        dateText.setText("Date: "+dayOfMonth+"/"+a+"/"+year);
                    }
                },year,month,day);
                dialog.show();
            }
        });

        String value= getIntent().getStringExtra("key");

        if(value.equals("one")){
            textView.setText("Come from Button One");
        }
        else if(value.equals("two")){
            textView.setText("Come from Button Two");
        }

    }
}