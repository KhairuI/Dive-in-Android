package com.example.classretrofit.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class CreatePost extends AppCompatActivity {

    private EditText id, title, text;
    private Button button;
    private TextView textView;
    private APIClient apiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        id= findViewById(R.id.userId);
        title= findViewById(R.id.userTitleId);
        text= findViewById(R.id.userTextId);
        textView= findViewById(R.id.finalResultId);
        button= findViewById(R.id.postButtonId);

        Retrofit retrofit= new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        apiClient= retrofit.create(APIClient.class);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userId= id.getText().toString();
                String userTitle= title.getText().toString();
                String userText= text.getText().toString();

                createPost(Integer.parseInt(userId),userTitle,userText);
            }
        });
    }

    private void createPost(int id, String userTitle, String userText) {

        Post post= new Post(id,userTitle,userText);
        Call<Post> call= apiClient.createPost(post);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(CreatePost.this, ""+response.code(), Toast.LENGTH_SHORT).show();
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
                Toast.makeText(CreatePost.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }
}