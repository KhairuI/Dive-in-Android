package com.example.classsharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences= getSharedPreferences("database", Context.MODE_PRIVATE);
        if(sharedPreferences.contains("status")){

            String value= sharedPreferences.getString("status","notLogin");

            if(value.equals("login")){
                Intent intent= new Intent(StartActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
            else if(value.equals("notLogin")){
                Intent intent= new Intent(StartActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }


        }
        else {
            Intent intent= new Intent(StartActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }

    }
}