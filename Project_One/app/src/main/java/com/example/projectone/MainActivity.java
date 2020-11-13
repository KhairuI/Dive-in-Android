package com.example.projectone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Spinner spinner;
    private Button submitButton;
    private TextView textView;
    private String[] playerName;
    private String[] playerType;
    private MyAdapter adapter;
    private int[] image={R.drawable.messi,R.drawable.shakib,
            R.drawable.neymar,R.drawable.mushfiqur,R.drawable.tamim};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner= findViewById(R.id.spinnerId);
        submitButton= findViewById(R.id.submitId);
        textView= findViewById(R.id.spinnerNameId);
        playerName= getResources().getStringArray(R.array.player);
        playerType=getResources().getStringArray(R.array.player_type);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value= textView.getText().toString();
                Intent intent= new Intent(MainActivity.this,ProfileActivity.class);
                intent.putExtra("player_name",value);
                startActivity(intent);
            }
        });

        adapter= new MyAdapter(MainActivity.this,playerName,image,playerType);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                textView.setText(playerName[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}