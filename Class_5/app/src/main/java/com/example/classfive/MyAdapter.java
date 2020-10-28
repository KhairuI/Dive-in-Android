package com.example.classfive;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {
    private String[] playerName;
    private String[] playerType;
    private int[] image;
    private Context context;

    public MyAdapter(String[] playerName, String[] playerType, int[] image, Context context) {
        this.playerName = playerName;
        this.playerType = playerType;
        this.image = image;
        this.context = context;
    }

    @Override
    public int getCount() {
        return playerName.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(view==null){
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view= inflater.inflate(R.layout.single_item,null,false);

        }
        ImageView imageView= view.findViewById(R.id.singleImageId);
        imageView.setImageResource(image[position]);

        TextView name= view.findViewById(R.id.singleNameId);
        name.setText(playerName[position]);

        TextView type= view.findViewById(R.id.singleTypeId);
        type.setText(playerType[position]);
        return view;
    }
}
