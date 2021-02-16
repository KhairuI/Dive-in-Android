package com.example.classretrofit.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.classretrofit.R;

public class MainActivity extends AppCompatActivity {

    private Button getPostButton,getComment,postButton,putButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getPostButton= findViewById(R.id.getPostButtonId);
        getPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, PostActivity.class);
                startActivity(intent);
            }
        });

        getComment= findViewById(R.id.getCommentButtonId);
        getComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, CommentActivity.class);
                startActivity(intent);
            }
        });

        postButton= findViewById(R.id.postButtonId);
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, CreatePost.class);
                startActivity(intent);
            }
        });

        putButton= findViewById(R.id.putButtonId);
        putButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, PutActivity.class);
                startActivity(intent);
            }
        });

    }
}