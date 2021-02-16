package com.example.class_mvvm.repository;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.example.class_mvvm.api.MyInterface;
import com.example.class_mvvm.api.UserDB;
import com.example.class_mvvm.model.Post;
import com.example.class_mvvm.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserRepository {

    // variable
    private UserDB userDB;
    private Context context;
    private FirebaseFirestore fireStore= FirebaseFirestore.getInstance();

    Retrofit retrofit= new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create()).build();
    MyInterface myInterface= retrofit.create(MyInterface.class);

    public MutableLiveData<List<Post>> getPost(){

        MutableLiveData<List<Post>> getMutableData= new MutableLiveData<>();

        Call<List<Post>> call= myInterface.getPost(new int[]{2,3});
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(!response.isSuccessful()){
                    return;
                }
                List<Post> postList= response.body();
                getMutableData.setValue(postList);
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {


            }
        });

        return getMutableData;
    }

    // insert Data in Room SQLite......

    public MutableLiveData<String> insertSQL(Context context1, User user){
        MutableLiveData<String> getInsertResult= new MutableLiveData<>();
        context= context1;
        userDB= Room.databaseBuilder(context,UserDB.class,"user_database")
                .allowMainThreadQueries().build();
        long value= userDB.userDAO().insertData(user);

        if(value==-1){
            getInsertResult.setValue("Insert Failed");
        }
        else {
            getInsertResult.setValue("Insert Success");
        }
        return  getInsertResult;
    }

    // get data from SQLite...

    public MutableLiveData<List<User>> getSQLiteData(Context context1){

        userDB= Room.databaseBuilder(context1,UserDB.class,"user_database")
                .allowMainThreadQueries().build();
        MutableLiveData<List<User>> getData= new MutableLiveData<>();
        List<User> userList= new ArrayList<>();
        userList= userDB.userDAO().readData();
        getData.setValue(userList);

        return getData;
    }

    // insert Data in firebase....

    public MutableLiveData<String> insertFirebase(User user){

        MutableLiveData<String> getInsertResult= new MutableLiveData<>();

        Map<String,String> dataMap= new HashMap<>();
        dataMap.put("name",user.getName());
        dataMap.put("age",user.getAge());

        fireStore.collection("user_data").document(randomDigit())
                .set(dataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){
                    getInsertResult.setValue("Insert Success");
                }
                else {
                    getInsertResult.setValue(task.getException().toString());
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                getInsertResult.setValue(e.toString());
            }
        });



        return getInsertResult;
    }

    // retrieve data from firebase....
    public MutableLiveData<List<User>> getFirebaseData(){
        List<User> userList= new ArrayList<>();
        MutableLiveData<List<User>> getData= new MutableLiveData<>();

        fireStore.collection("user_data").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if(task.isSuccessful()){
                    for(DocumentSnapshot ds: task.getResult()){
                        String name= ds.getString("name");
                        String age= ds.getString("age");
                        User user= new User(name,age);
                        userList.add(user);
                    }
                    getData.setValue(userList);
                }
            }
        });

        return getData;
    }

    //generate a random digit.........
    private String randomDigit() {

        char[] chars= "1234567890".toCharArray();
        StringBuilder stringBuilder= new StringBuilder();
        Random random= new Random();
        for(int i=0;i<4;i++){
            char c= chars[random.nextInt(chars.length)];
            stringBuilder.append(c);
        }
        return stringBuilder.toString();


    }

}
