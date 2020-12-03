package com.example.classsqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton button;
    private DataBaseHelper dataBaseHelper;
    private List<Model> playerList;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataBaseHelper= new DataBaseHelper(MainActivity.this);
        SQLiteDatabase sqLiteDatabase= dataBaseHelper.getWritableDatabase();
        adapter= new MyAdapter();

        recyclerView= findViewById(R.id.recycleViewId);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        button= findViewById(R.id.insertButtonId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,InsertActivity.class);
                startActivity(intent);
            }
        });

        loadData();

        adapter.setOnItemListener(new MyAdapter.OnItemClick() {
            @Override
            public void onItemClick(int position) {

                Model details= playerList.get(position);
                Intent intent= new Intent(MainActivity.this,DetailsActivity.class);
                intent.putExtra("details",details);
                startActivity(intent);

            }

            @Override
            public void onLongItemClick(final int position) {
                AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Delete").setMessage("Do you want to delete ?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteData(position);
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create().show();

            }
        });
    }

    private void deleteData(int position) {
        String id= playerList.get(position).getId();
        int value= dataBaseHelper.delete(id);
        if(value>0){
            Toast.makeText(this, "Delete Successfully", Toast.LENGTH_SHORT).show();
            playerList.remove(position);
            adapter.notifyItemRemoved(position);
        }
        else {
            Toast.makeText(this, "Delete Failed", Toast.LENGTH_SHORT).show();
        }


    }


    private void loadData() {
        playerList= new ArrayList<>();
        Cursor cursor= dataBaseHelper.show();
        if(cursor.getCount()==0){
            Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show();
        }
        else {

            while (cursor.moveToNext()){

                String id= String.valueOf(cursor.getString(0));
                String name= cursor.getString(1);
                String type= cursor.getString(2);
                String code= String.valueOf(cursor.getString(3));

                Model model= new Model(id,name,code,type);
                playerList.add(model);
            }
        }
        adapter.getPlayerList(playerList);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater= getMenuInflater();
        menuInflater.inflate(R.menu.menu_item,menu);

        MenuItem menuItem= menu.findItem(R.id.searchId);
        SearchView searchView= (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String st) {
                adapter.getFilter().filter(st);
                return false;
            }
        });


        return true;
    }
}