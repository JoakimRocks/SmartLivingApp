package com.example.smartliving.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartliving.Interface.ItemClickListener;
import com.example.smartliving.R;

public class RoomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView houseDescription;
    public TextView roomName;
    public ImageView roomImage;
    private ItemClickListener itemClickListener;

    public RoomViewHolder(@NonNull View itemView) {
        super(itemView);
        houseDescription = (TextView)itemView.findViewById(R.id.house1_description);
        roomImage = (ImageView)itemView.findViewById(R.id.room_1_image);
        roomName = (TextView)itemView.findViewById(R.id.tv_room_1);

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

