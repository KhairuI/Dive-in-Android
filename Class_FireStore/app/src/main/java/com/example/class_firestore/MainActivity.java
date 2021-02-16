package com.example.class_firestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton insertButton,searchButton;
    private ContactAdapter adapter;
    private List<User> userList;
    private FirebaseFirestore firebaseFirestore= FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView= findViewById(R.id.recycleViewId);
        insertButton= findViewById(R.id.insertButtonId);
        searchButton= findViewById(R.id.searchButtonId);
        adapter= new ContactAdapter();
        userList= new ArrayList<>();
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,InsertActivity.class);
                startActivity(intent);
            }
        });
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,SearchActivity.class);
                startActivity(intent);
            }
        });

        setRecycle();
    }

    private void setRecycle() {
        firebaseFirestore.collection("User_Data").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    userList.clear();
                    for (DocumentSnapshot ds: task.getResult()){
                        String name= ds.getString("name");
                        String age= ds.getString("age");
                        User user= new User(name,age);
                        userList.add(user);
                    }
                    adapter.getContactList(userList);
                    recyclerView.setAdapter(adapter);
                }
                else {
                    Toast.makeText(MainActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}