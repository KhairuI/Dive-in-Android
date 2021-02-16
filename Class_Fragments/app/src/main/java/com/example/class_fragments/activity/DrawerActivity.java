package com.example.class_fragments.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.class_fragments.R;
import com.example.class_fragments.activity.fragment.ChatFrag;
import com.example.class_fragments.activity.fragment.ProfileFrag;
import com.google.android.material.navigation.NavigationView;

public class DrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        toolbar= findViewById(R.id.drawerToolbarId);
        setSupportActionBar(toolbar);
        drawerLayout= findViewById(R.id.drawerLayoutId);
        NavigationView navigationView= findViewById(R.id.navigationId);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.drawer_open,R.string.drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutId,new ProfileFrag()).commit();
            navigationView.setCheckedItem(R.id.profileId);
        }


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()== R.id.profileId){

            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutId,new ProfileFrag()).commit();
        }
        else if(item.getItemId()==R.id.chatId){
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutId,new ChatFrag()).commit();

        }
        else if(item.getItemId()==R.id.settingId){

            Toast.makeText(this, "Setting", Toast.LENGTH_SHORT).show();
        }
        else if(item.getItemId()==R.id.shareId){
            Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }

    }
}