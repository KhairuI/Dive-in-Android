package com.example.classroomdatabase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PlayerDAO {

    @Insert
    public long insertData(Player player);

    @Query("SELECT * FROM  player_table")
    public List<Player> readData();

    @Query("UPDATE player_table SET name= :name , code= :code, type= :type WHERE id= :id")
    public int updateData(String name, String code,String type, int id);

    @Query("DELETE FROM player_table")
    public void deleteAll();

    @Query("DELETE FROM player_table WHERE id= :id")
    public int deleteData(int id);

    @Query("SELECT * FROM player_table WHERE type= :type")
    public List<Player> typeRead(String type);




}
