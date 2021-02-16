package com.example.class_mvvm.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.class_mvvm.R;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private Button plusButton, minusButton,roomButton,firebaseButton, webButton;
    private int a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView= findViewById(R.id.textViewId);
        plusButton= findViewById(R.id.plusButtonId);
        minusButton= findViewById(R.id.minusButtonId);
        roomButton=findViewById(R.id.roomButtonId);
        firebaseButton= findViewById(R.id.firebaseButtonId);
        webButton= findViewById(R.id.webButtonId);
        webButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(MainActivity.this,WebActivity.class);
                startActivity(intent);
            }
        });

        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String value= textView.getText().toString();
                a= Integer.parseInt(value)+1;
                textView.setText(String.valueOf(a));
            }
        });

        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value= textView.getText().toString();
                a= Integer.parseInt(value)-1;
                textView.setText(String.valueOf(a));
            }
        });

        roomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,RoomActivity.class);
                startActivity(intent);
            }
        });

        firebaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,FirebaseActivity.class);
                startActivity(intent);
            }
        });

        if(savedInstanceState != null){
           a= savedInstanceState.getInt("count");
            textView.setText(String.valueOf(a));
        }


    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("count",a);
        super.onSaveInstanceState(outState);


    }


}