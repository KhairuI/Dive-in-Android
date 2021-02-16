package com.example.classfirebase.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classfirebase.R;
import com.example.classfirebase.model.Image;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyViewHolder> {

    private List<Image> imageList;

    public ImageAdapter(List<Image> imageList) {
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.single_image,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView.setText(imageList.get(position).getImageName());
        holder.singleImage.setImageURI(imageList.get(position).getUri());
        holder.deleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageList.remove(position);
                notifyDataSetChanged();
            }
        });


    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView singleImage,deleteImage;
        private TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView= itemView.findViewById(R.id.singleImageTextId);
            singleImage= itemView.findViewById(R.id.singleImageId);
            deleteImage= itemView.findViewById(R.id.singleDeleteButtonId);
        }
    }


}

