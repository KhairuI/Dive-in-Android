package com.example.classsqlite;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Model> playerList;
    private List<Model> searchList;
    private OnItemClick onItemClick;

    public void getPlayerList(List<Model> playerList){
        this.playerList= playerList;
        searchList= new ArrayList<>(playerList);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.singleCode.setText("Code: "+playerList.get(position).getCode());
        holder.singleName.setText("Name: "+playerList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return playerList.size();
    }

    public Filter getFilter(){
        return playerFilter;
    }


    private Filter playerFilter= new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence ch) {
            List<Model> filterUser= new ArrayList<>();
            if(ch ==null || ch.length()==0){
                filterUser.addAll(searchList);
            }
            else {
                String filterPattern= ch.toString().toLowerCase().trim();
                for (Model model:searchList){
                    if(model.getCode().toLowerCase().contains(ch) || model.getName().toLowerCase().contains(ch)){
                        filterUser.add(model);
                    }
                }

            }

            FilterResults filterResults= new FilterResults();
            filterResults.values= filterUser;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            playerList.clear();
            playerList.addAll((List<Model>)results.values);
            notifyDataSetChanged();

        }
    };



    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView singleName, singleCode;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            singleName= itemView.findViewById(R.id.singleNameId);
            singleCode= itemView.findViewById(R.id.singleCodeId);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position= getAdapterPosition();
                    if(onItemClick != null && position != RecyclerView.NO_POSITION){
                        onItemClick.onItemClick(position);
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position= getAdapterPosition();
                    if(onItemClick != null && position != RecyclerView.NO_POSITION){
                        onItemClick.onLongItemClick(position);
                    }
                    return false;
                }
            });

        }
    }

    public interface OnItemClick{

        void onItemClick(int position);
        void onLongItemClick(int position);
    }

    public void setOnItemListener(OnItemClick onItemClick){

        this.onItemClick= onItemClick;
    }
   /* public void setOnLongItemListener(OnItemClick onItemClick){

        this.onItemClick= onItemClick;
    }*/

}
