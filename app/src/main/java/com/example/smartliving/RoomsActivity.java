package com.example.smartliving;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.smartliving.Interface.ItemClickListener;
import com.example.smartliving.Model.House;
import com.example.smartliving.Model.Rooms;
import com.example.smartliving.ViewHolder.RoomViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class RoomsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ViewPager viewPager;

    DatabaseReference rooms;
    FirebaseDatabase database;
    String houseId="";

    FirebaseRecyclerAdapter<Rooms, RoomViewHolder> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms);

        database = FirebaseDatabase.getInstance();
        rooms = database.getReference("Rooms");

        recyclerView = (RecyclerView)findViewById(R.id.view_pager);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        if(getIntent() != null)
            houseId = getIntent().getStringExtra("HouseId");
        if(!houseId.isEmpty()){
            loadRooms(houseId);
        }
    }

    private void loadRooms(String categoryId) {
        adapter = new FirebaseRecyclerAdapter<Rooms, RoomViewHolder>(Rooms.class, R.layout.view_pager_item, RoomViewHolder.class,
                rooms.orderByChild("Description").equalTo(categoryId)){
            @Override
            protected void populateViewHolder(RoomViewHolder roomViewHolder, Rooms rooms, int i) {
                roomViewHolder.roomName.setText(rooms.getName1());
                roomViewHolder.roomName.setText(rooms.getName2());
                roomViewHolder.roomName.setText(rooms.getName3());
                roomViewHolder.roomName.setText(rooms.getName4());
                roomViewHolder.roomName.setText(rooms.getName5());

                Picasso.with(getBaseContext()).load(rooms.getImage1())
                        .into(roomViewHolder.roomImage);
                Picasso.with(getBaseContext()).load(rooms.getImage2())
                        .into(roomViewHolder.roomImage);
                Picasso.with(getBaseContext()).load(rooms.getImage3())
                        .into(roomViewHolder.roomImage);
                Picasso.with(getBaseContext()).load(rooms.getImage4())
                        .into(roomViewHolder.roomImage);
                Picasso.with(getBaseContext()).load(rooms.getImage5())
                        .into(roomViewHolder.roomImage);

                final Rooms local = rooms;
                roomViewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent houseDetail = new Intent(RoomsActivity.this, HouseListActivity.class);
                        houseDetail.putExtra("HouseId",adapter.getRef(position).getKey());
                        startActivity(houseDetail);
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);

    }
}
