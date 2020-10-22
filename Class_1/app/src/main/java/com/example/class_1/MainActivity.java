package com.example.class_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private AppCompatTextView textView;
    private AppCompatButton button;
    private AppCompatEditText editText;
    private AppCompatButton loginButton,changeImage;
    private AppCompatImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView= findViewById(R.id.textViewId);
        button= findViewById(R.id.buttonId);
        editText= findViewById(R.id.editTextId);
        loginButton= findViewById(R.id.loginButtonId);
        imageView= findViewById(R.id.imageId);
        changeImage= findViewById(R.id.changeImageId);
        textView.setText("Dive In Android");
        button.setOnClickListener(this);
        loginButton.setOnClickListener(this);
        changeImage.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.buttonId){
            Toast.makeText(this, "DIU", Toast.LENGTH_SHORT).show();
        }
        else if(v.getId()==R.id.loginButtonId){
            String text= editText.getEditableText().toString();

            Toast.makeText(this, "Name: "+text, Toast.LENGTH_SHORT).show();
        }
        else if(v.getId()==R.id.changeImageId){
            imageView.setImageResource(R.drawable.google);
        }

    }

}