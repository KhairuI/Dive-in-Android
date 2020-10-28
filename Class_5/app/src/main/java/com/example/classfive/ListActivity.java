package com.example.classfive;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class ListActivity extends AppCompatActivity {
    private ListView listView;
    private String[] playerName;
    private String[] playerType;
    private MyAdapter adapter;
    private int[] image={R.drawable.messi,R.drawable.shakib,
            R.drawable.neymar,R.drawable.mushfiqur,R.drawable.tamim,R.drawable.bill,
            R.drawable.mark,R.drawable.jamal,R.drawable.siddikur,R.drawable.virat};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView= findViewById(R.id.listViewId);
        playerName= getResources().getStringArray(R.array.player);
        playerType= getResources().getStringArray(R.array.player_type);
        adapter= new MyAdapter(playerName,playerType,image,ListActivity.this);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String value= playerName[position];
                Toast.makeText(ListActivity.this, ""+value, Toast.LENGTH_SHORT).show();

            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder= new AlertDialog.Builder(ListActivity.this);
                builder.setTitle("Delete").setIcon(R.drawable.ic_delete).setMessage("Do you want to delete ?")
                        .setCancelable(true).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String value= playerName[position];
                        Toast.makeText(ListActivity.this, "Delete: "+value, Toast.LENGTH_SHORT).show();
                    }
                }).create().show();

                return true;
            }
        });


    }
}