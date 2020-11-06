package com.example.smartliving.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartliving.Interface.ItemClickListener;
import com.example.smartliving.R;

public class HouseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView house_name;
    public ImageView house_image;
    private ItemClickListener itemClickListener;


    public HouseViewHolder(@NonNull View itemView) {
        super(itemView);
        house_name = (TextView)itemView.findViewById(R.id.house_name);
        house_image = (ImageView)itemView.findViewById(R.id.house_image);

        itemView.setOnClickListener(this);

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);
    }
}
