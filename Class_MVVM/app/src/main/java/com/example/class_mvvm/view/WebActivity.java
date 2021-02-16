package com.example.class_mvvm.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.class_mvvm.R;
import com.example.class_mvvm.adapter.MyAdapter;
import com.example.class_mvvm.model.Post;
import com.example.class_mvvm.viewmodel.UserViewModel;

import java.util.List;

public class WebActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UserViewModel userViewModel;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        intViewModel();

        recyclerView= findViewById(R.id.postRecycleId);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        userViewModel.getPost();
        userViewModel.getLiveData.observe(this, new Observer<List<Post>>() {
            @Override
            public void onChanged(List<Post> posts) {
                adapter= new MyAdapter(posts);
                recyclerView.setAdapter(adapter);

            }
        });

    }

    private void intViewModel() {
        userViewModel= new ViewModelProvider(this,ViewModelProvider
                .AndroidViewModelFactory.getInstance(this.getApplication())).get(UserViewModel.class);
    }
}