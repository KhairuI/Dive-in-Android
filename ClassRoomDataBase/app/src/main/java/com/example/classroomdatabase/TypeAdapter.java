package com.example.classroomdatabase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TypeAdapter extends RecyclerView.Adapter<TypeAdapter.TypeViewHolder> {

    private List<Player> typePlayerList;

    public void getTypePlayerList(List<Player> typePlayerList){

        this.typePlayerList= typePlayerList;
    }


    @NonNull
    @Override
    public TypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.type_single_item,parent,false);
        return new TypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TypeViewHolder holder, int position) {

        holder.typePlayerName.setText("Name: "+typePlayerList.get(position).getName());
        holder.typePlayerCode.setText("Code: "+typePlayerList.get(position).getCode());
        holder.typePlayerType.setText("Type: "+typePlayerList.get(position).getType());

    }

    @Override
    public int getItemCount() {
        return typePlayerList.size();
    }

    public class TypeViewHolder extends RecyclerView.ViewHolder{
        TextView typePlayerName, typePlayerCode,typePlayerType;

        public TypeViewHolder(@NonNull View itemView) {
            super(itemView);

            typePlayerCode= itemView.findViewById(R.id.typeSingleCodeId);
            typePlayerName= itemView.findViewById(R.id.typeSingleNameId);
            typePlayerType= itemView.findViewById(R.id.typeSingleTypeId);

        }
    }
}
