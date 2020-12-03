package com.example.classsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {
    private EditText codeEditText,nameEditText,typeEditText;
    private Button insertButton,listButton;
    private DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        dataBaseHelper= new DataBaseHelper(InsertActivity.this);
        SQLiteDatabase sqLiteDatabase= dataBaseHelper.getWritableDatabase();

        codeEditText= findViewById(R.id.insertCodeId);
        nameEditText= findViewById(R.id.insertNameId);
        typeEditText= findViewById(R.id.insertTypeId);
        insertButton= findViewById(R.id.insertButtonId);
        listButton= findViewById(R.id.listButtonId);

        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name= nameEditText.getText().toString();
                String type= typeEditText.getText().toString();
                String code= codeEditText.getText().toString();
                insertData(name,type,code);

            }
        });

        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(InsertActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void insertData(String name, String type, String code) {
        long value= dataBaseHelper.insert(name,type,code);

        if(value==-1){
            Toast.makeText(this, "Insert Failed", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Insert Success", Toast.LENGTH_SHORT).show();
            nameEditText.getText().clear();
            typeEditText.getText().clear();
            codeEditText.getText().clear();
        }

    }
}