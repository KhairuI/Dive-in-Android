package com.example.class_mvvm.model;

import com.google.gson.annotations.SerializedName;

public class Post {

    private int userId;
    private Integer id;
    private String title;
    public Boolean isPost= false;

    @SerializedName("body")
    private String text;

    public Post() {
    }

    public Post(int userId, String title, String text) {
        this.userId = userId;
        this.title = title;
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getPost() {
        return isPost;
    }

    public void setPost(Boolean post) {
        isPost = post;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
