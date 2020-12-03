package com.example.classsharedpreference;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private LinearLayout layout;
    private Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout= findViewById(R.id.linearId);
        logoutButton= findViewById(R.id.logoutId);
        layout.setBackgroundColor(getColor());

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences= getSharedPreferences("database", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor= sharedPreferences.edit();
                editor.putString("status","notLogin");
                editor.commit();

                Intent intent= new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_item,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.redId){
            layout.setBackgroundColor(getResources().getColor(R.color.red));
            setColor(getResources().getColor(R.color.red));
        }
        else if(item.getItemId()==R.id.greenId){

            layout.setBackgroundColor(getResources().getColor(R.color.green));
            setColor(getResources().getColor(R.color.green));

        }

        return super.onOptionsItemSelected(item);
    }

    private void setColor(int color) {

        SharedPreferences sharedPreferences= getSharedPreferences("database", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putInt("bgColor",color);
        editor.commit();

    }

    private int getColor(){

        SharedPreferences sharedPreferences= getSharedPreferences("database", Context.MODE_PRIVATE);
        int value= sharedPreferences.getInt("bgColor",0);
        return value;

    }
}