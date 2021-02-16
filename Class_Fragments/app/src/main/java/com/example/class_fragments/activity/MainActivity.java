package com.example.class_fragments.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.class_fragments.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNav;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNav= findViewById(R.id.bottomNavigationId);
        navController= Navigation.findNavController(this,R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(bottomNav,navController);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.fragmentId){

            Intent intent= new Intent(MainActivity.this,FragmentActivity.class);
            startActivity(intent);
        }
        else if(item.getItemId()==R.id.tabId){
            Intent intent= new Intent(MainActivity.this,TabActivity.class);
            startActivity(intent);

        }
        else if(item.getItemId()==R.id.drawerId){
            Intent intent= new Intent(MainActivity.this,DrawerActivity.class);
            startActivity(intent);

        }


        return super.onOptionsItemSelected(item);
    }
}