package com.example.classlifecycle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Player> playerList;

    public MyAdapter(List<Player> playerList) {
        this.playerList = playerList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(playerList.get(position).getName());
        holder.code.setText(playerList.get(position).getCode());
        holder.type.setText(playerList.get(position).getType());

        boolean isExpand= playerList.get(position).isExpand();

        if (isExpand){
            holder.layout.setVisibility(View.VISIBLE);
        }
        else {
            holder.layout.setVisibility(View.GONE);
        }



    }

    @Override
    public int getItemCount() {
        return playerList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView name, code,type;
        private LinearLayout layout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name= itemView.findViewById(R.id.singleNameId);
            code= itemView.findViewById(R.id.singleCodeId);
            type= itemView.findViewById(R.id.singleTypeId);
            layout= itemView.findViewById(R.id.details);

            name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Player player= playerList.get(getAdapterPosition());
                    player.setExpand(!player.isExpand());
                    notifyItemChanged(getAdapterPosition());

                }
            });
        }
    }

}
