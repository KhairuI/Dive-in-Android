package com.example.class_firestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private EditText equal, greater, less;
    private Button equalButton, greaterButton, lessButton;
    private FirebaseFirestore firebaseFirestore= FirebaseFirestore.getInstance();
    private List<User> users;
    private ContactAdapter adapter;
    private RecyclerView searchRecycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        equal= findViewById(R.id.equalEditTextId);
        greater= findViewById(R.id.greaterEditTextId);
        less= findViewById(R.id.lessEditTextId);
        users= new ArrayList<>();
        adapter= new ContactAdapter();
        searchRecycle= findViewById(R.id.searchRecycleView);
        searchRecycle.setHasFixedSize(false);
        searchRecycle.setLayoutManager(new LinearLayoutManager(this));

        equalButton= findViewById(R.id.equalButtonId);
        greaterButton= findViewById(R.id.greaterButtonId);
        lessButton= findViewById(R.id.lessButtonId);

        equalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String value= equal.getText().toString();
                firebaseFirestore.collection("User_Data").whereEqualTo("age",value).get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if(task.isSuccessful()){
                                    users.clear();
                                    for(DocumentSnapshot ds: task.getResult()){
                                        String name= ds.getString("name");
                                        String age= ds.getString("age");
                                        User user= new User(name,age);
                                        users.add(user);
                                    }
                                    adapter.getContactList(users);
                                    searchRecycle.setAdapter(adapter);


                                }
                                else {
                                    Toast.makeText(SearchActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

            }
        });
        greaterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String value= greater.getText().toString();

                firebaseFirestore.collection("User_Data").whereGreaterThanOrEqualTo("age",value).get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if(task.isSuccessful()){
                                    users.clear();
                                    for(DocumentSnapshot ds: task.getResult()){
                                        String name= ds.getString("name");
                                        String age= ds.getString("age");
                                        User user= new User(name,age);
                                        users.add(user);
                                    }
                                    adapter.getContactList(users);
                                    searchRecycle.setAdapter(adapter);


                                }
                                else {
                                    Toast.makeText(SearchActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
            }
        });
        lessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value= less.getText().toString();

                firebaseFirestore.collection("User_Data").whereLessThanOrEqualTo("age",value).get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if(task.isSuccessful()){
                                    users.clear();
                                    for(DocumentSnapshot ds: task.getResult()){
                                        String name= ds.getString("name");
                                        String age= ds.getString("age");
                                        User user= new User(name,age);
                                        users.add(user);
                                    }
                                    adapter.getContactList(users);
                                    searchRecycle.setAdapter(adapter);


                                }
                                else {
                                    Toast.makeText(SearchActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

            }
        });
    }
}