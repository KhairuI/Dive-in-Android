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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.classfirebase.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class SetNameActivity extends AppCompatActivity {

    private Button submitButton,confirmButton;
    private TextView textView;
    private EditText editText;
    private ProgressBar progressBar;
    private RelativeLayout relativeLayout;

    // firebase
    private FirebaseAuth firebaseAuth= FirebaseAuth.getInstance();
    private FirebaseDatabase database= FirebaseDatabase.getInstance();
    DatabaseReference mRef= database.getReference("User_Info");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_name);

        findSection();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name= editText.getText().toString();
                if(!TextUtils.isEmpty(name)){
                    progressBar.setVisibility(View.VISIBLE);
                    name= name.replace(" ","");
                    String newName= name.toLowerCase().trim();

                    mRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if(checkUserName(newName,snapshot)){
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(SetNameActivity.this, "This name is exist".toString(), Toast.LENGTH_SHORT).show();
                            }
                            else {
                                progressBar.setVisibility(View.GONE);
                                relativeLayout.setVisibility(View.VISIBLE);
                                textView.setText(newName);
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(SetNameActivity.this, ""+error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                else {
                    Toast.makeText(SetNameActivity.this, "Enter username", Toast.LENGTH_SHORT).show();
                }
            }
        });
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                FirebaseUser currentUser= firebaseAuth.getCurrentUser();
                if(currentUser != null){

                    String id= currentUser.getUid();
                    String name= textView.getText().toString();
                    String email= currentUser.getEmail();
                    String image= "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_640.png";

                    Map<String,String> userInfo= new HashMap<>();
                    userInfo.put("email",email);
                    userInfo.put("image",image);
                    userInfo.put("name",name);

                    DatabaseReference infoRef= mRef.child(id);
                    infoRef.setValue(userInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(SetNameActivity.this, "Set Name Success", Toast.LENGTH_SHORT).show();
                                goToMainActivity();

                            }
                            else {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(SetNameActivity.this, "Set Name Failed", Toast.LENGTH_SHORT).show();
                                goToLoginActivity();
                            }

                        }
                    });



                }
                else {
                    Toast.makeText(SetNameActivity.this, "User null", Toast.LENGTH_SHORT).show();
                }



            }
        });
    }

    private void goToLoginActivity() {
        Intent intent= new Intent(SetNameActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK); // clear the activity stack...
        startActivity(intent);
    }

    private void goToMainActivity() {
        Intent intent= new Intent(SetNameActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK); // clear the activity stack...
        startActivity(intent);
    }

    private boolean checkUserName(String name, DataSnapshot dataSnapshot){

        for(DataSnapshot ds:dataSnapshot.getChildren()){

            String a= ds.child("name").getValue().toString();
            if(a.equals(name)){
                return true;
            }
        }
        return false;
    }

    private void findSection() {

        submitButton= findViewById(R.id.submitButtonId);
        confirmButton= findViewById(R.id.confirmButtonId);
        textView= findViewById(R.id.finalNameTextId);
        editText= findViewById(R.id.setUserNameEditTextId);
        progressBar= findViewById(R.id.setNameProgressId);
        progressBar.setVisibility(View.GONE);
        relativeLayout= findViewById(R.id.layout3);
        relativeLayout.setVisibility(View.GONE);

    }
}