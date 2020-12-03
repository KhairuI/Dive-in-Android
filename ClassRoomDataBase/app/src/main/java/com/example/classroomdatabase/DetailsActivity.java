package com.example.classroomdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    private TextView indexText,nameText,codeText,typeText;
    private Button updateButton;
    private Toolbar detailsToolbar;
    private TextView toolbarText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        final Player detailsPlayer= (Player) getIntent().getSerializableExtra("details");

        detailsToolbar= findViewById(R.id.detailsToolbarId);
        toolbarText= findViewById(R.id.toolbarTextId);
        toolbarText.setText("Details");
        setSupportActionBar(detailsToolbar);

        indexText= findViewById(R.id.detailsIndexId);
        nameText= findViewById(R.id.detailsNameId);
        codeText= findViewById(R.id.detailsCodeId);
        typeText= findViewById(R.id.detailsTypeId);
        updateButton= findViewById(R.id.updateButtonId);

        //set Text.....

        indexText.setText("Index ID: "+Integer.toString(detailsPlayer.getId()));
        nameText.setText("Name: "+detailsPlayer.getName());
        codeText.setText("Code: "+detailsPlayer.getCode());
        typeText.setText("Type: "+detailsPlayer.getType());

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(DetailsActivity.this,UpdateActivity.class);
                intent.putExtra("update",detailsPlayer);
                startActivity(intent);

            }
        });



    }
}