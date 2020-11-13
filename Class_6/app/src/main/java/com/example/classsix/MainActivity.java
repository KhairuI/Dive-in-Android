package com.example.classsix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class MainActivity extends AppCompatActivity implements ClickInterface{

    private String[] playerName;
    private List<Model> playerList;
    private Model model;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private MyAdapter adapter;
    private String deletePlayer="";


    private int[] image={R.drawable.messi,R.drawable.shakib,
            R.drawable.neymar,R.drawable.mushfiqur,R.drawable.tamim,R.drawable.bill,
            R.drawable.mark,R.drawable.jamal,R.drawable.siddikur,R.drawable.virat};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playerList= new ArrayList<>();
        recyclerView= findViewById(R.id.recycleViewId);
        refreshLayout= findViewById(R.id.refreshId);

        playerName= getResources().getStringArray(R.array.player);

        for(int i=0;i<playerName.length;i++){
            model= new Model(image[i],playerName[i]);
            playerList.add(model);
        }

        adapter= new MyAdapter(playerList,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                model= new Model(R.drawable.mark,"Mark Jukarbarg");
                playerList.add(model);
                adapter.notifyDataSetChanged();
                refreshLayout.setRefreshing(false);
            }
        });

        ItemTouchHelper itemTouchHelper= new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }

    ItemTouchHelper.SimpleCallback simpleCallback= new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP| ItemTouchHelper
            .END|ItemTouchHelper.DOWN|ItemTouchHelper.START,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder,
                              @NonNull RecyclerView.ViewHolder target) {

            int fromPosition= viewHolder.getAdapterPosition();
            int toPosition= target.getAdapterPosition();
            Collections.swap(playerList,fromPosition,toPosition);
            recyclerView.getAdapter().notifyItemMoved(fromPosition,toPosition);
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            final int position= viewHolder.getAdapterPosition();
            if(direction== ItemTouchHelper.LEFT || direction== ItemTouchHelper.RIGHT ){

                deletePlayer= playerList.get(position).getName();
                final Model myModel= playerList.get(position);
                playerList.remove(position);
                adapter.notifyItemRemoved(position);

                Snackbar.make(recyclerView,deletePlayer+" is Deleted",Snackbar.LENGTH_LONG)
                        .setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        playerList.add(position,myModel);
                        adapter.notifyItemInserted(position);
                    }
                }).show();

            }

        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder,
                                float dX, float dY, int actionState, boolean isCurrentlyActive) {

            new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.colorRed))
                    .addSwipeLeftActionIcon(R.drawable.ic_delete)
                    .addSwipeLeftLabel("Delete")
                    .addSwipeRightBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.colorRed))
                    .addSwipeRightActionIcon(R.drawable.ic_delete)
                    .create()
                    .decorate();

            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };

    @Override
    public void onItemClick(int position) {

        Toast.makeText(this, ""+playerList.get(position).getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLongItemClick(final int position) {

        AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Delete").setIcon(R.drawable.ic_delete).setMessage("Do you want to delete ?")
                .setCancelable(true).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               playerList.remove(position);
               adapter.notifyItemRemoved(position);
            }
        }).create().show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater= getMenuInflater();
        menuInflater.inflate(R.menu.menu_item,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.contactId){

            Intent intent= new Intent(MainActivity.this,ContactActivity.class);
            startActivity(intent);

        }
        if(item.getItemId()==R.id.settingId){
            Toast.makeText(this, "Setting", Toast.LENGTH_SHORT).show();
        }
        if(item.getItemId()==R.id.aboutId){
            Toast.makeText(this, "About", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}