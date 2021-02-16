package com.example.classretrofit.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classretrofit.R;
import com.example.classretrofit.model.Comment;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyViewHolder>{

    private List<Comment> userList;

    public CommentAdapter(List<Comment> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view= inflater.inflate(R.layout.single_item,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.title.setText("ID: "+userList.get(position).getPostId()+"\n"
                +"Post ID: "+userList.get(position).getId()+"\n"+"Title: "+userList.get(position).getName()+"\n"+
                "Text: "+userList.get(position).getEmail()+"\n"+"Body: "+userList.get(position).getText());

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private final TextView title;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title= itemView.findViewById(R.id.singleTitleId);

        }
    }
}
