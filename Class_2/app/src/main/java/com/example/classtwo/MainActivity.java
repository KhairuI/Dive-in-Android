package com.example.classtwo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button sampleButton,customButton;
    private Button errorToast,successToast,infoToast,snackBar;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sampleButton= findViewById(R.id.sampleToastId);
        customButton= findViewById(R.id.customToastId);
        errorToast= findViewById(R.id.errorToastId);
        successToast= findViewById(R.id.successToastId);
        infoToast = findViewById(R.id.infoToastId);
        snackBar= findViewById(R.id.snackBarId);
        linearLayout= findViewById(R.id.linearLayout);
        errorToast.setOnClickListener(this);
        successToast.setOnClickListener(this);
        infoToast.setOnClickListener(this);
        snackBar.setOnClickListener(this);


        sampleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Sample Toast", Toast.LENGTH_SHORT).show();
            }
        });

        customButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cusTomToasT();
            }
        });
    }

    private void cusTomToasT() {
        LayoutInflater inflater= getLayoutInflater();
        View view= inflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.customViewId));

        TextView textView= view.findViewById(R.id.customTextId);
        textView.setText("Login Success");
        Toast toast= new Toast(MainActivity.this);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.show();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.errorToastId:
                Toasty.error(MainActivity.this,"This is error",Toast.LENGTH_SHORT).show();
                break;

            case R.id.successToastId:
                Toasty.success(MainActivity.this,"Success",Toasty.LENGTH_SHORT).show();
                break;

            case R.id.infoToastId:
                Toasty.info(MainActivity.this,"Info Toast",Toasty.LENGTH_SHORT).show();
                break;
            case R.id.snackBarId:
                snackMethod();
                break;


        }
    }

    private void snackMethod() {
        Snackbar.make(linearLayout,"this is snackbar",Snackbar.LENGTH_SHORT)
                .setAction("Undo", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(linearLayout,"Undo Success",Snackbar.LENGTH_SHORT).show();
            }
        }).show();

    }
}