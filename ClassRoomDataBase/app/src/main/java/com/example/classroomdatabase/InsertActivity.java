package com.example.classroomdatabase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar insertToolbar;
    private TextView toolbarTextView,typeTextView;
    private Button saveButton,listButton,pickButton;
    private EditText nameEditText,codeEditText;
    private PlayerDB playerDB;

    private RadioGroup radioGroup;
    private RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        setUpDB();

        saveButton= findViewById(R.id.saveButtonId);
        listButton= findViewById(R.id.listButtonId);
        pickButton= findViewById(R.id.pickButtonId);
        typeTextView= findViewById(R.id.playerTypeTextId);
        nameEditText= findViewById(R.id.insertNameId);
        codeEditText= findViewById(R.id.insertCodeId);
        insertToolbar= findViewById(R.id.insertToolbarId);
        toolbarTextView= findViewById(R.id.toolbarTextId);
        toolbarTextView.setText("Insert");
        setSupportActionBar(insertToolbar);

        saveButton.setOnClickListener(this);
        listButton.setOnClickListener(this);
        pickButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId()==R.id.saveButtonId){

            String name= nameEditText.getText().toString();
            String code= codeEditText.getText().toString();
            String type= typeTextView.getText().toString();

            if(name.equals("") || code.equals("") || type.equals("Player Type")|| type.equals("")){
                Toast.makeText(InsertActivity.this, "Filled all TextField", Toast.LENGTH_SHORT).show();
            }
            else {
                insertInfo(name,code,type);
            }

        }
        else if(v.getId()==R.id.listButtonId){

            Intent intent= new Intent(InsertActivity.this,MainActivity.class);
            startActivity(intent);
            finish();

        }
        else if(v.getId()==R.id.pickButtonId){

            openDialogue();
        }

    }

    private void insertInfo(String name, String code, String type) {
        Player player= new Player(name,code,type);
        long value= playerDB.playerDAO().insertData(player);
        if(value==-1){
            Toast.makeText(this, "Insert Failed", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Insert Success", Toast.LENGTH_SHORT).show();
        }
    }

    private void openDialogue() {
        AlertDialog.Builder builder= new AlertDialog.Builder(InsertActivity.this);
        LayoutInflater inflater= this.getLayoutInflater();
        final View view= inflater.inflate(R.layout.type_dialogue,null);
        builder.setView(view).setTitle("Choose a Item").setCancelable(true)
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        radioGroup= view.findViewById(R.id.radioGroupId);
                        int select= radioGroup.getCheckedRadioButtonId();
                        radioButton= view.findViewById(select);
                        typeTextView.setText(radioButton.getText().toString());
                    }
                }).setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).create().show();
    }

    private void setUpDB() {
        playerDB= Room.databaseBuilder(InsertActivity.this,PlayerDB.class,"player_database")
                .allowMainThreadQueries().build();
    }
}