package com.example.project_02;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private FloatingActionButton insertButton;
    private EditText nameEditText,typeEditText;
    private List<Model> playerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recycleViewId);
        insertButton= findViewById(R.id.insertButtonId);
        playerList= new ArrayList<>();
        adapter= new MyAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setHasFixedSize(true);

        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeInfo();
            }
        });
    }

    private void takeInfo() {
        AlertDialog.Builder dialogue= new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater= this.getLayoutInflater();
        final View view= inflater.inflate(R.layout.insert_item,null);
        dialogue.setView(view).setCancelable(true).setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                nameEditText= view.findViewById(R.id.insertNameId);
                typeEditText= view.findViewById(R.id.insertTypeId);

                String name= nameEditText.getText().toString();
                String type= typeEditText.getText().toString();
                Model model= new Model(name,type);
                playerList.add(model);
                adapter.getPlayerList(playerList);
                recyclerView.setAdapter(adapter);


            }
        }).setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).create().show();


    }
}