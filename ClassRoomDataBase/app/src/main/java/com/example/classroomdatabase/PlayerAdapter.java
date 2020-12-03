package com.example.classroomdatabase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.MyViewHolder>{
    private List<Player> playerList;

    private OnItemClick onItemClick;
    private MainActivity mainActivity;

    public PlayerAdapter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void getPlayerList(List<Player> playerList){
        this.playerList= playerList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item,parent,false);

        return new MyViewHolder(view,mainActivity);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.singleCode.setText("Code: "+playerList.get(position).getCode());
        holder.singleName.setText("Name: "+playerList.get(position).getName());

        if(!mainActivity.isEnable){
            holder.checkBox.setVisibility(View.GONE);
        }
        else {
            holder.checkBox.setVisibility(View.VISIBLE);
            holder.checkBox.setChecked(false);
        }



    }

    @Override
    public int getItemCount() {
        return playerList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView singleName, singleCode;
        private CheckBox checkBox;

        public MyViewHolder(@NonNull View itemView, final MainActivity mainActivity) {
            super(itemView);
            singleName= itemView.findViewById(R.id.singleNameId);
            singleCode= itemView.findViewById(R.id.singleCodeId);
            checkBox= itemView.findViewById(R.id.singleCheckId);

            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mainActivity.selectItem(v,getAdapterPosition());
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position= getAdapterPosition();
                    if(onItemClick != null &&  position != RecyclerView.NO_POSITION){
                        onItemClick.onItemClick(position);
                    }

                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position= getAdapterPosition();
                    if(onItemClick != null &&  position != RecyclerView.NO_POSITION){
                        onItemClick.onLongItemClick(v);
                    }
                    return false;
                }
            });
        }
    }

    public interface OnItemClick{
        void onItemClick(int position);
        void onLongItemClick(View view);
    }

    public void setOnItemListener(OnItemClick onItemClick){
        this.onItemClick= onItemClick;
    }



}
