package com.example.class_fragments.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.class_fragments.R;
import com.example.class_fragments.activity.fragment.FragmentA;
import com.example.class_fragments.activity.fragment.FragmentB;

public class FragmentActivity extends AppCompatActivity {
    private Button buttonA, buttonB;
    private Fragment selcetFragment= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        buttonA= findViewById(R.id.buttonAId);
        buttonB= findViewById(R.id.buttonBId);

        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selcetFragment= new FragmentA();
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutId,selcetFragment)
                        .commit();

            }
        });
        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selcetFragment= new FragmentB();
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutId,selcetFragment)
                        .commit();
            }
        });

    }
}