package com.example.classlifecycle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Player> playerList;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        recyclerView= findViewById(R.id.recycleId);
        setData();
        setRecycle();
    }

    private void setRecycle() {
        adapter= new MyAdapter(playerList);
        recyclerView.setLayoutManager(new LinearLayoutManager(ListActivity.this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    private void setData() {
        playerList= new ArrayList<>();
        playerList.add(new Player("Shakib","75","Cricket"));
        playerList.add(new Player("Tamim","28","Cricket"));
        playerList.add(new Player("Messi","10","Football"));
        playerList.add(new Player("neymar","10","Football"));
        playerList.add(new Player("Shakib","75","Cricket"));
    }
}