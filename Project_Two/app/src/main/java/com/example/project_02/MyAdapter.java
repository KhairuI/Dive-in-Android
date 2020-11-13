package com.example.project_02;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<Model> playerList;

    public void getPlayerList(List<Model> playerList){
        this.playerList= playerList;
    }
    
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.nameTextView.setText(playerList.get(position).getPlayerName());
        holder.typeTextView.setText(playerList.get(position).getPlayerType());
    }

    @Override
    public int getItemCount() {
        return playerList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView,typeTextView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTextView= itemView.findViewById(R.id.singleNameId);
            typeTextView= itemView.findViewById(R.id.singleTypeId);

        }
    }
}
