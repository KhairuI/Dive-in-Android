package com.example.projectone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {
    private TextView heading,details;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        heading= findViewById(R.id.detailsHeadingId);
        details= findViewById(R.id.detailsId);
        imageView= findViewById(R.id.profileImageId);

        String value= getIntent().getStringExtra("player_name");

        if(value.equals("Messi")){
            setTitle("Messi");
            imageView.setImageResource(R.drawable.messi);
            heading.setText("Messi Details");
            details.setText(R.string.Messi);
        }
        else if(value.equals("Shakib")){
            setTitle("Shakib");
            imageView.setImageResource(R.drawable.shakib);
            heading.setText("Shakib Details");
            details.setText(R.string.Shakib);

        }
        else if(value.equals("Neymar")){
            setTitle("Neymar");
            imageView.setImageResource(R.drawable.neymar);
            heading.setText("Neymar Details");
            details.setText(R.string.Neymar);

        }
        else if(value.equals("Mushfique")){
            setTitle("Mushfique");
            imageView.setImageResource(R.drawable.mushfiqur);
            heading.setText("Mushfique Details");
            details.setText(R.string.Mushfique);

        }
        else if(value.equals("Tamim")){
            setTitle("Tamim");
            imageView.setImageResource(R.drawable.tamim);
            heading.setText("Tamim Details");
            details.setText(R.string.Tamim);

        }

    }
}