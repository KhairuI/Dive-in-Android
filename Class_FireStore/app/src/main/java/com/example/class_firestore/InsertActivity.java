package com.example.class_firestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class InsertActivity extends AppCompatActivity {

    private EditText name, age;
    private Button button,show;

    private FirebaseFirestore firebaseFirestore= FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        name= findViewById(R.id.nameId);
        age= findViewById(R.id.ageId);
        button= findViewById(R.id.saveButtonId);
        show= findViewById(R.id.showButtonId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id= randomDigit();
                String username= name.getText().toString();
                String userAge= age.getText().toString();
                SaveData(id,username,userAge);

            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(InsertActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void SaveData(String id, String username, String userAge) {

        Map<String,String> dataMap= new HashMap<>();
        dataMap.put("id",id);
        dataMap.put("name",username);
        dataMap.put("age",userAge);


        firebaseFirestore.collection("User_Data").document(id).set(dataMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(InsertActivity.this, "Insert Success", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(InsertActivity.this, ""+e.toString(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    //generate a random digit.........
    private String randomDigit() {

        char[] chars= "1234567890".toCharArray();
        StringBuilder stringBuilder= new StringBuilder();
        Random random= new Random();
        for(int i=0;i<4;i++){
            char c= chars[random.nextInt(chars.length)];
            stringBuilder.append(c);
        }
        return stringBuilder.toString();


    }
}