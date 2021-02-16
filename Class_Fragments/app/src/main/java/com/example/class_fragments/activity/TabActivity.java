package com.example.class_fragments.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.class_fragments.R;
import com.example.class_fragments.activity.fragment.DashFragment;
import com.example.class_fragments.activity.fragment.HomeFragment;
import com.example.class_fragments.activity.fragment.PageAdapter;
import com.google.android.material.tabs.TabLayout;

public class TabActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        tabLayout= findViewById(R.id.tabLayoutId);
        viewPager= findViewById(R.id.viewPagerId);
        setViewpager();
    }

    private void setViewpager() {
        PageAdapter adapter= new PageAdapter(getSupportFragmentManager(),0);
        adapter.addFragment(new HomeFragment());
        adapter.addFragment(new DashFragment());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_desh_board);
    }
}