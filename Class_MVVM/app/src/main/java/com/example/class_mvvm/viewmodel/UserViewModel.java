package com.example.class_mvvm.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.class_mvvm.model.Post;
import com.example.class_mvvm.model.User;
import com.example.class_mvvm.repository.UserRepository;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private UserRepository repository;
    public LiveData<List<Post>> getLiveData;

    public LiveData<String> insertSQLResult;
    public LiveData<String> insertFirebaseResult;
    public LiveData<List<User>> getSQLData;
    public LiveData<List<User>> getFirebaseFireStoreData;

    public UserViewModel(@NonNull Application application) {
        super(application);
        repository= new UserRepository();
    }

    // insert Firebase...

    public void insertFirebase(User user){
        insertFirebaseResult= repository.insertFirebase(user);
    }

    // Retrieve firebase
    public void  getFirebaseData(){
        getFirebaseFireStoreData= repository.getFirebaseData();
    }


    // get Web data....
    public void getPost(){
        getLiveData= repository.getPost();
    }

    // insert SQL Data...
    public void insertSQL(Context context, User user){

        insertSQLResult= repository.insertSQL(context, user);
    }

    // get Data from SQL....

    public  void getSQLiteData(Context context){
        getSQLData= repository.getSQLiteData(context);
    }

}
