package com.example.class_mvvm.api;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.class_mvvm.model.User;

import java.util.List;

@Dao
public interface UserDAO {

    @Insert
    public long insertData(User user);

    @Query("SELECT * FROM user_table")
    public List<User> readData();

}
