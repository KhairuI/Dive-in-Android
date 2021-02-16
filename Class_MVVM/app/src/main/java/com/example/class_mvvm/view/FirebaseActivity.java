package com.example.class_mvvm.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.class_mvvm.R;
import com.example.class_mvvm.adapter.UserAdapter;
import com.example.class_mvvm.model.User;
import com.example.class_mvvm.viewmodel.UserViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class FirebaseActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FloatingActionButton button;

    private UserViewModel userViewModel;
    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);
        intViewModel();
        recyclerView= findViewById(R.id.firebaseRecycleId);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        button= findViewById(R.id.firebaseInsertButtonId);

        loadData();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(FirebaseActivity.this,InsertActivity.class);
                intent.putExtra("key","firebase");
                startActivity(intent);
            }
        });
    }

    private void loadData() {

        userViewModel.getFirebaseData();
        userViewModel.getFirebaseFireStoreData.observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                adapter= new UserAdapter(users);
                recyclerView.setAdapter(adapter);
            }
        });

    }

    private void intViewModel() {
        userViewModel= new ViewModelProvider(this,ViewModelProvider
                .AndroidViewModelFactory.getInstance(this.getApplication())).get(UserViewModel.class);
    }
}