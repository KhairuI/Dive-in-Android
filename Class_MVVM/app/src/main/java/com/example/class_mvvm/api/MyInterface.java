package com.example.class_mvvm.api;

import com.example.class_mvvm.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MyInterface {

    @GET("posts")
    Call<List<Post>> getPost(@Query("userId") int[] userId);

}
