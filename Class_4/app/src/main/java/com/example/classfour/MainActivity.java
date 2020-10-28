package com.example.classfour;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class MainActivity extends AppCompatActivity {
    private Button button,valueButton;
    private NumberPicker numberPicker;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button= findViewById(R.id.buttonId);
        valueButton= findViewById(R.id.valueId);
        numberPicker= findViewById(R.id.numberId);
        imageView= findViewById(R.id.imageId);

        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);

        valueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value= Integer.toString(numberPicker.getValue());
                Toast.makeText(MainActivity.this, ""+value, Toast.LENGTH_SHORT).show();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImage();
            }
        });

    }

    private void pickImage() {
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
            }
            else {
                uploadImage();
            }

        }
        else {
            uploadImage();
        }
    }

    private void uploadImage() {
        CropImage.activity().setGuidelines(CropImageView.Guidelines.ON)
                .setAspectRatio(1,1).start(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result= CropImage.getActivityResult(data);
            if(resultCode==RESULT_OK){
                Uri uri= result.getUri();
                imageView.setImageURI(uri);

            }
            else if(requestCode== CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
                Exception e= result.getError();
                Toast.makeText(this, ""+e.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
        }

    }
}