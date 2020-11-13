package com.example.classsix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ContactActivity extends AppCompatActivity {

    private EditText phoneEditText,toEditText,subEditText,messageEditText;
    private ImageView phoneImage,sentImage;
    public static final int request_call=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        phoneEditText= findViewById(R.id.phoneEditTextId);
        toEditText= findViewById(R.id.toEditTextId);
        subEditText= findViewById(R.id.subjectEditTextId);
        messageEditText= findViewById(R.id.mesageEditTextId);
        phoneImage= findViewById(R.id.phoneIconId);
        sentImage= findViewById(R.id.sentIconId);

        phoneImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                makePhone();
            }
        });
        sentImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sentmail();
            }
        });

    }

    private void sentmail() {
        String to= toEditText.getText().toString();
        String[] makeTo= to.split(",");
        String subject= subEditText.getText().toString();
        String message= messageEditText.getText().toString();

        Intent intent= new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL,makeTo);
        intent.putExtra(Intent.EXTRA_SUBJECT,subject);
        intent.putExtra(Intent.EXTRA_TEXT,message);
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent,"Choose an email client"));
    }

    private void makePhone() {
        String number= phoneEditText.getText().toString();
        if(number.trim().length()>0){
            if(ContextCompat.checkSelfPermission(ContactActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(ContactActivity.this,new String[] {Manifest.permission.CALL_PHONE},request_call);

            }
            else {
                String dial= "tel:"+number;
                Intent intent= new Intent(Intent.ACTION_DIAL, Uri.parse(dial));
                startActivity(intent);
            }
        }
        else {
            Toast.makeText(this, "Please Enter number", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==request_call){
            if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                makePhone();
            }
        }
        else {
            Toast.makeText(this, "Denied", Toast.LENGTH_SHORT).show();
        }

    }
}