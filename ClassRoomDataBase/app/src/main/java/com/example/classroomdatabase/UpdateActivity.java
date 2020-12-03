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

public class UpdateActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar updateToolbar;
    private TextView tollBarText,indexTextView,updatePlayerText;
    private EditText nameEditText,codeEditText;
    private Button updatePickButton,playerUpdateButton;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private PlayerDB playerDB;

    private Player updatePlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        setUpDB();

        updatePlayer= (Player) getIntent().getSerializableExtra("update");

        updateToolbar= findViewById(R.id.updateToolbarId);
        tollBarText= findViewById(R.id.toolbarTextId);
        tollBarText.setText("Update");
        setSupportActionBar(updateToolbar);

        //view find...
        indexTextView= findViewById(R.id.updateIndexId);
        nameEditText= findViewById(R.id.updatePlayerNameId);
        codeEditText= findViewById(R.id.updateCodeId);
        updatePlayerText= findViewById(R.id.updatePlayerTypeTextId);

        //button
        updatePickButton= findViewById(R.id.updatePickButtonId);
        playerUpdateButton= findViewById(R.id.updatePlayerButtonId);

        updatePickButton.setOnClickListener(this);
        playerUpdateButton.setOnClickListener(this);

        ///set value.....
        indexTextView.setText("Player ID: "+updatePlayer.getId());
        nameEditText.setText(updatePlayer.getName());
        codeEditText.setText(updatePlayer.getCode());
        updatePlayerText.setText(updatePlayer.getType());

    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.updatePickButtonId){

            openDialogue();

        }
        else if(v.getId()==R.id.updatePlayerButtonId){

            int id= updatePlayer.getId();
            String name= nameEditText.getText().toString();
            String code= codeEditText.getText().toString();
            String type= updatePlayerText.getText().toString();
            updateData(id,name,code,type);
        }


    }

    private void updateData(int id, String name, String code, String type) {

        int value= playerDB.playerDAO().updateData(name,code,type,id);
        if(value==-1){
            Toast.makeText(this, "Update Fail", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Update Success", Toast.LENGTH_SHORT).show();
            Intent intent= new Intent(UpdateActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }

    }

    private void openDialogue() {
        AlertDialog.Builder builder= new AlertDialog.Builder(UpdateActivity.this);
        LayoutInflater inflater= this.getLayoutInflater();
        final View view= inflater.inflate(R.layout.type_dialogue,null);
        builder.setView(view).setTitle("Choose a Item").setCancelable(true)
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        radioGroup= view.findViewById(R.id.radioGroupId);
                        int select= radioGroup.getCheckedRadioButtonId();
                        radioButton= view.findViewById(select);
                        updatePlayerText.setText(radioButton.getText().toString());
                    }
                }).setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).create().show();
    }

    private void setUpDB() {
        playerDB= Room.databaseBuilder(UpdateActivity.this,PlayerDB.class,"player_database")
                .allowMainThreadQueries().build();
    }
}