package com.example.classretrofit.api;

import com.example.classretrofit.model.Comment;
import com.example.classretrofit.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIClient {

    @GET("posts")
    Call<List<Post>> getPost(@Query("userId") int[] userId);

    @GET("posts/{id}/comments")
    Call<List<Comment>> getComment(@Path("id") int commentId);

    // post request.....
    @POST("posts")
    Call<Post> createPost(@Body Post post);

    //put post
    @PUT("posts/{id}")
    Call<Post> putPost(@Path("id") int id,@Body Post post); // completely replace....

    // patch post...
    @PATCH("posts/{id}")
    Call<Post> patchPost(@Path("id") int id,@Body Post post); // update existing filed....

    // delete post
    @DELETE("posts/{id}")
    Call<Void> deletePost(@Path("id") int id);  // delete existing field

}
// posts/2/comments