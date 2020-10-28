package com.example.classfive;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Button imagePickButton,listButton;
    private ImageView imageView;
    public static final int GALLERY_REQUEST=1;
    public static final int CAMERA_REQUEST=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imagePickButton= findViewById(R.id.pickImageButtonId);
        listButton=findViewById(R.id.listButtonId);
        imageView= findViewById(R.id.imageViewId);
        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(MainActivity.this,ListActivity.class);
                startActivity(intent);
            }
        });
        imagePickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogue();
            }
        });
    }

    private void openDialogue() {
        AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
        String[] option={"Gallery","Camera"};
        builder.setItems(option, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(which==0){
                    openGallery();
                }
                if(which==1){
                    openCamera();
                }
            }
        }).create().show();
    }

    private void openCamera() {
        Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,CAMERA_REQUEST);
    }

    private void openGallery() {
        Intent intent= new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,GALLERY_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==GALLERY_REQUEST && resultCode==RESULT_OK){
            Uri uri= data.getData();
            imageView.setImageURI(uri);

        }
        if(requestCode==CAMERA_REQUEST && resultCode==RESULT_OK){

            Bitmap bitmap= (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);

        }



    }
}