package com.example.classroomdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TypeActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar typeToolbar;
    private TextView toolTextView;
    private CardView cricketCard,footballCard,tennisCard,golfCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);

        typeToolbar= findViewById(R.id.typeToolbarId);
        toolTextView= findViewById(R.id.toolbarTextId);
        toolTextView.setText("All Types");
        setSupportActionBar(typeToolbar);

        //find Card View
        cricketCard= findViewById(R.id.cricketCardId);
        footballCard= findViewById(R.id.footballCardId);
        tennisCard= findViewById(R.id.tennisCardId);
        golfCard= findViewById(R.id.golfCardId);

        cricketCard.setOnClickListener(this);
        footballCard.setOnClickListener(this);
        tennisCard.setOnClickListener(this);
        golfCard.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.cricketCardId){
            goToIntent("Cricket");

        }
        else if(v.getId()==R.id.footballCardId){
            goToIntent("Football");

        }
        else if(v.getId()==R.id.tennisCardId){
            goToIntent("Tennis");

        }
        else if(v.getId()==R.id.golfCardId){
            goToIntent("Golf");

        }



    }

    private void goToIntent(String s) {
        Intent intent= new Intent(TypeActivity.this,TypeDetailsActivity.class);
        intent.putExtra("type",s);
        startActivity(intent);
    }
}