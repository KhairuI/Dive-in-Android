package com.example.classroomdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TypeDetailsActivity extends AppCompatActivity {

    private RecyclerView typeRecycle;
    private Toolbar typeDetailsToolbar;
    TextView toolbarText;
    private PlayerDB playerDB;
    private String type;
    private List<Player> typeList;
    private TypeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_details);
        setUpDB();
        type= getIntent().getStringExtra("type");

        typeDetailsToolbar= findViewById(R.id.typeDetailsToolBarId);
        toolbarText= findViewById(R.id.toolbarTextId);
        toolbarText.setText(type+" List");
        setSupportActionBar(typeDetailsToolbar);
        typeList= new ArrayList<>();

        typeRecycle= findViewById(R.id.typeRecycleViewId);
        typeRecycle.setLayoutManager(new LinearLayoutManager(TypeDetailsActivity.this));
        typeRecycle.setHasFixedSize(true);
        adapter= new TypeAdapter();
        loadTypeData();




    }

    private void loadTypeData() {

        typeList= playerDB.playerDAO().typeRead(type);
        adapter.getTypePlayerList(typeList);
        typeRecycle.setAdapter(adapter);

    }

    private void setUpDB() {
        playerDB= Room.databaseBuilder(TypeDetailsActivity.this,PlayerDB.class,"player_database").allowMainThreadQueries().build();
    }
}