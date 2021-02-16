package com.example.classfirebase.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SplashActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth= FirebaseAuth.getInstance();
    private FirebaseDatabase database= FirebaseDatabase.getInstance();
    DatabaseReference mRef= database.getReference("User_Info");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if(currentUser != null){
            mRef.child(currentUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        gotToMainActivity();
                    }
                    else {
                        gotToLoginActivity();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(SplashActivity.this, ""+error.toString(), Toast.LENGTH_SHORT).show();
                    gotToLoginActivity();

                }
            });

        }
        else {
            gotToLoginActivity();
        }

    }

    private void gotToMainActivity() {
        Intent intent= new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void gotToLoginActivity() {
        Intent intent= new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}