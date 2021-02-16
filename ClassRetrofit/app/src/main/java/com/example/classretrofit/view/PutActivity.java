package com.example.classretrofit.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.classretrofit.R;
import com.example.classretrofit.api.APIClient;
import com.example.classretrofit.model.Post;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PutActivity extends AppCompatActivity {

    private Button putButton,patchButton,deleteButton;
    private TextView textView;
    private APIClient apiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_put);

        textView= findViewById(R.id.putFinalResultId);
        putButton= findViewById(R.id.putButtonId);
        patchButton= findViewById(R.id.patchButtonId);
        deleteButton=findViewById(R.id.deleteButtonId);
        Retrofit retrofit= new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        apiClient= retrofit.create(APIClient.class);
        
        putButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putPost();
            }
        });
        patchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                patchPost();
            }
        });
        
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletePost();
            }
        });
    }

    private void deletePost() {
        Call<Void> call= apiClient.deletePost(4);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                textView.setText("Code: "+response.code());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

                Toast.makeText(PutActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void patchPost() {

        Post post= new Post(23,null,"My new  Patch Text Here");
        Call<Post> call= apiClient.patchPost(5,post);
        call.enqueue(new Callback<Post>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                if(!response.isSuccessful()){
                    Toast.makeText(PutActivity.this, ""+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Post post1= response.body();
                textView.setText("Code: "+response.code()+"\n"+
                        "ID: "+post1.getId()+"\n"+
                        "User ID: "+post1.getUserId()+"\n"+
                        "Title: "+post1.getTitle()+"\n"+
                        "Text: "+post1.getText());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

                Toast.makeText(PutActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void putPost() {

        Post post= new Post(23,null,"My new Text Here");
        Call<Post> call= apiClient.putPost(5,post);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                if(!response.isSuccessful()){
                    Toast.makeText(PutActivity.this, ""+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Post post1= response.body();
                textView.setText("Code: "+response.code()+"\n"+
                        "ID: "+post1.getId()+"\n"+
                        "User ID: "+post1.getUserId()+"\n"+
                        "Title: "+post1.getTitle()+"\n"+
                        "Text: "+post1.getText());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

                Toast.makeText(PutActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}