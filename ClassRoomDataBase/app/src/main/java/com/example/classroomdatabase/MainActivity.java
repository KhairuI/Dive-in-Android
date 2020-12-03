package com.example.classroomdatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar mainToolBar;
    private TextView toolbarTextView;
    private FloatingActionButton insertButton;
    private RecyclerView recyclerView;
    private PlayerDB playerDB;
    private PlayerAdapter adapter;
    private List<Player> playerList;
    private List<Player> selectedList;
    boolean isEnable= false;
    int count= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpDB();

        insertButton= findViewById(R.id.insertButtonId);
        mainToolBar= findViewById(R.id.mainToolbarId);
        toolbarTextView= findViewById(R.id.toolbarTextId);
        toolbarTextView.setText("Player List");
        setSupportActionBar(mainToolBar);

        recyclerView= findViewById(R.id.recycleViewId);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setHasFixedSize(true);
        adapter= new PlayerAdapter(MainActivity.this);
        playerList= new ArrayList<>();
        selectedList= new ArrayList<>();

        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,InsertActivity.class);
                startActivity(intent);
            }
        });
        loadData();
        adapter.setOnItemListener(new PlayerAdapter.OnItemClick() {
            @Override
            public void onItemClick(int position) {

                Player player= playerList.get(position);
                Intent intent= new Intent(MainActivity.this,DetailsActivity.class);
                intent.putExtra("details",player);
                startActivity(intent);

            }

            @Override
            public void onLongItemClick(View view) {

                mainToolBar.getMenu().clear();
                mainToolBar.inflateMenu(R.menu.context_menu);
                toolbarTextView.setText("0 Item Selected");
                isEnable= true;
                adapter.notifyDataSetChanged();
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);



            }
        });
    }

    private void loadData() {

        playerList= playerDB.playerDAO().readData();
        adapter.getPlayerList(playerList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.normal_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.deleteAllId){
            playerDB.playerDAO().deleteAll();
            playerList.clear();
            adapter.notifyDataSetChanged();
        }
        else if(item.getItemId()==R.id.allTypeId){
            Intent intent = new Intent(MainActivity.this,TypeActivity.class);
            startActivity(intent);

        }
        else if(item.getItemId()==R.id.deleteId){
            int size= selectedList.size();
            for(int i=0;i<size;i++){

                int id= selectedList.get(i).getId();
                playerDB.playerDAO().deleteData(id);
                playerList.remove(selectedList.get(i));
            }
            adapter.notifyDataSetChanged();
            clearActionBar();

        }
        else if(item.getItemId()== android.R.id.home){
            clearActionBar();
            adapter.notifyDataSetChanged();

        }



        return super.onOptionsItemSelected(item);
    }

    private void clearActionBar() {
        isEnable= false;
        mainToolBar.getMenu().clear();
        mainToolBar.inflateMenu(R.menu.normal_menu);
        toolbarTextView.setText("Player List");
        count=0;
        selectedList.clear();

    }

    public void selectItem(View view, int i){

        if(((CheckBox)view).isChecked()){

            selectedList.add(playerList.get(i));
            count= count+1;
            updateCounter(count);

        }
        else {
            selectedList.remove(playerList.get(i));
            count=count-1;
            updateCounter(count);

        }

    }

    private void updateCounter(int count) {

        if(count==0){
            toolbarTextView.setText("0 Item Selected");
        }
        else {
            toolbarTextView.setText(count+" Item Selected");
        }
    }

    @Override
    public void onBackPressed() {
        if(isEnable){
            clearActionBar();
            adapter.notifyDataSetChanged();
        }
        else {
            super.onBackPressed();
        }

        super.onBackPressed();
    }

    private void setUpDB() {
        playerDB= Room.databaseBuilder(MainActivity.this,PlayerDB.class,"player_database")
                .allowMainThreadQueries().build();
    }
}