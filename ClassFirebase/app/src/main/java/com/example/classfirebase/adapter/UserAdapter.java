package com.example.classfirebase.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classfirebase.R;
import com.example.classfirebase.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<User> userList;
    private final ClickInterface clickInterface;

    public UserAdapter(ClickInterface clickInterface) {
        this.clickInterface = clickInterface;
    }

    public void getUserList(List<User> userList){
        this.userList= userList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View view= layoutInflater.inflate(R.layout.single_list,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {

        holder.name.setText("Name: "+userList.get(position).getNoteName());
        holder.description.setText("Description: "+userList.get(position).getNoteDescription());
        holder.total.setText("Total Images: "+userList.get(position).getTotalImage());

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private TextView name,description,total;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            name= itemView.findViewById(R.id.singleListNameId);
            description= itemView.findViewById(R.id.singleListDescriptionId);
            total= itemView.findViewById(R.id.singleListCountId);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {

            clickInterface.onItemClick(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View view) {
            clickInterface.onLongItemClick(getAdapterPosition());
            return false;
        }
    }

    public interface ClickInterface {
        // for on Click....
        void onItemClick(int position);
        void onLongItemClick(int position);
    }

}
