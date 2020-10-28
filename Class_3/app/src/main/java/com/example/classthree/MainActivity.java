package com.example.classthree;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonOne,buttonTwo;
    private Spinner spinner;
    private String[] name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // find Section....
        buttonOne= findViewById(R.id.buttonOneId);
        buttonTwo= findViewById(R.id.buttonTwoId);
        spinner= findViewById(R.id.spinnerId);
        buttonOne.setOnClickListener(this);
        buttonTwo.setOnClickListener(this);


        name= getResources().getStringArray(R.array.country);
        ArrayAdapter<CharSequence> adapter= ArrayAdapter.
                createFromResource(this,R.array.country,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String text= parent.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity.this, ""+text, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.buttonOneId){
            Intent intent= new Intent(MainActivity.this,SecondActivity.class);
            intent.putExtra("key","one");
            startActivity(intent);
        }
        else if(v.getId()==R.id.buttonTwoId){
            Intent intent= new Intent(MainActivity.this,SecondActivity.class);
            intent.putExtra("key","two");
            startActivity(intent);
        }
    }
}