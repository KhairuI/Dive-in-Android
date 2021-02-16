package com.example.classfirebase.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classfirebase.R;
import com.example.classfirebase.utils.SquareImage;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.List;

public class GridImageAdapter extends ArrayAdapter<String> {

    private Context context;
    private LayoutInflater inflater;
    private  int layoutRes;
    private String append;
    private List<String> imageList;

    public GridImageAdapter(Context context, int layoutRes, String append, List<String> imageList) {
        super(context,layoutRes,imageList);
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.layoutRes = layoutRes;
        this.append = append;
        this.imageList = imageList;
    }

    private static class MyViewHolder{
        SquareImage image;
        ProgressBar progressBar;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        final MyViewHolder viewHolder;
        if(view ==null){
            view= inflater.inflate(layoutRes,parent,false);
            viewHolder= new MyViewHolder();
            viewHolder.image= view.findViewById(R.id.singleGridImageId);
            viewHolder.progressBar= view.findViewById(R.id.gridProgressId);
            view.setTag(viewHolder);

        }
        else {
            viewHolder= (MyViewHolder) view.getTag();
        }

        String url= getItem(position);
        ImageLoader imageLoader= ImageLoader.getInstance();
        imageLoader.displayImage(append + url, viewHolder.image, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                if(viewHolder.progressBar != null){
                    viewHolder.progressBar.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

                if(viewHolder.progressBar != null){
                    viewHolder.progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {

                if(viewHolder.progressBar != null){
                    viewHolder.progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {

                if(viewHolder.progressBar != null){
                    viewHolder.progressBar.setVisibility(View.GONE);
                }
            }
        });


        return view;
    }
}
