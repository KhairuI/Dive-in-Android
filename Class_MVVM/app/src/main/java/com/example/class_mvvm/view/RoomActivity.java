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

public class RoomActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton button;

    private UserViewModel userViewModel;
    private UserAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        intViewModel();

        button= findViewById(R.id.roomInsertButtonId);
        recyclerView= findViewById(R.id.roomRecycleId);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        loadData();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(RoomActivity.this,InsertActivity.class);
                intent.putExtra("key","room");
                startActivity(intent);
            }
        });
    }

    private void loadData() {

        userViewModel.getSQLiteData(this);
        userViewModel.getSQLData.observe(this, new Observer<List<User>>() {
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