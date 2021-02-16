package com.example.class_mvvm.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.class_mvvm.R;
import com.example.class_mvvm.model.User;
import com.example.class_mvvm.viewmodel.UserViewModel;

public class InsertActivity extends AppCompatActivity {

    private EditText nameEditText,ageEditText;
    private Button button,show;
    private String value= null;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        intViewModel();

        value= getIntent().getStringExtra("key").toString();
        setTitle(value);


        nameEditText= findViewById(R.id.insertNameId);
        ageEditText= findViewById(R.id.insertAgeId);
        button= findViewById(R.id.saveButtonId);
        show= findViewById(R.id.listButtonId);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(value.equals("room")){
                    String name= nameEditText.getText().toString();
                    String age= ageEditText.getText().toString();
                    setRoom(name,age);
                }
                else if(value.equals("firebase")){

                    String name= nameEditText.getText().toString();
                    String age= ageEditText.getText().toString();
                    setFirebase(name,age);
                }
            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(value.equals("room")){
                    Intent intent= new Intent(InsertActivity.this,RoomActivity.class);
                    startActivity(intent);
                    finish();
                }
                else if(value.equals("firebase")){

                    Intent intent= new Intent(InsertActivity.this,FirebaseActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void setFirebase(String name, String age) {
        User user= new User(name,age);
        userViewModel.insertFirebase(user);
        userViewModel.insertFirebaseResult.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(InsertActivity.this, ""+s, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setRoom(String name, String age) {
        User user= new User(name,age);

        userViewModel.insertSQL(this,user);
        userViewModel.insertSQLResult.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(InsertActivity.this, ""+s, Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void intViewModel() {
        userViewModel= new ViewModelProvider(this,ViewModelProvider
                .AndroidViewModelFactory.getInstance(this.getApplication())).get(UserViewModel.class);
    }
}