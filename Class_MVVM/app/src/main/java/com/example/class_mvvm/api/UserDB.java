package com.example.class_mvvm.api;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.class_mvvm.model.Post;
import com.example.class_mvvm.model.User;

@Database(entities ={User.class} ,version = 1)
public abstract class UserDB extends RoomDatabase {

    public  abstract UserDAO userDAO();
}
