package com.example.classretrofit.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.classretrofit.R;
import com.example.classretrofit.adapter.CommentAdapter;
import com.example.classretrofit.adapter.MyAdapter;
import com.example.classretrofit.api.APIClient;
import com.example.classretrofit.model.Comment;
import com.example.classretrofit.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommentActivity extends AppCompatActivity {

    private EditText editText;
    private Button button;
    private RecyclerView recyclerView;
    private APIClient apiClient;
    private CommentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        editText= findViewById(R.id.commentEditTextId);
        button= findViewById(R.id.commentGoButtonId);
        recyclerView= findViewById(R.id.commentRecycleId);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        Retrofit retrofit= new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        apiClient= retrofit.create(APIClient.class);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value= editText.getText().toString();

                getComment(Integer.parseInt(value));
            }
        });

    }

    private void getComment(int num) {

        Call<List<Comment>> call= apiClient.getComment(num);
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(CommentActivity.this, ""+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Comment> comments= response.body();
                adapter= new CommentAdapter(comments);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                Toast.makeText(CommentActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}