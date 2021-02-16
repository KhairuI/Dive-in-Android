package com.example.classfirebase.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.classfirebase.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private EditText emailEditText,passwordEditText;
    private Button signInButton;
    private TextView textView;
    private ProgressBar progressBar;

    //firebase
    private FirebaseAuth firebaseAuth= FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findSection();

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email= emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){

                    if(password.length()>=6){

                        userSignIn(email,password);
                    }
                    else {
                        Toast.makeText(RegisterActivity.this, "Password should at least 6 character", Toast.LENGTH_SHORT).show();
                    }

                }
                else {
                    Toast.makeText(RegisterActivity.this, "Fill all the field", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void userSignIn(String email, String password) {
        progressBar.setVisibility(View.VISIBLE);
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser currentUser= firebaseAuth.getCurrentUser(); // get current user
                    currentUser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(RegisterActivity.this, "Send a verification mail. Check Your mail", Toast.LENGTH_SHORT).show();
                            Intent intent= new Intent(RegisterActivity.this,LoginActivity.class);
                            startActivity(intent);
                            finish();


                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressBar.setVisibility(View.GONE);
                            firebaseAuth.signOut();
                            Toast.makeText(RegisterActivity.this, ""+e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });


                }
                else {
                    Toast.makeText(RegisterActivity.this, ""+task.getException().toString(), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void findSection() {

        emailEditText= findViewById(R.id.signInEmailId);
        passwordEditText= findViewById(R.id.signInPasswordId);
        signInButton= findViewById(R.id.signInButtonId);
        textView= findViewById(R.id.loginTextId);
        progressBar= findViewById(R.id.signInProgressId);
        progressBar.setVisibility(View.GONE);
    }
}