package com.example.class_firestore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder>{

    private List<User> userList;

    public void getContactList(List<User> userList){
        this.userList= userList;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View view= layoutInflater.inflate(R.layout.single_item,parent,false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {

        holder.username.setText(userList.get(position).getName());
        holder.userAge.setText(userList.get(position).getAge());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }


    public class ContactViewHolder extends RecyclerView.ViewHolder {

        private TextView username,userAge;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            username= itemView.findViewById(R.id.singleNameId);
            userAge= itemView.findViewById(R.id.singleAgeId);
        }

    }


}
