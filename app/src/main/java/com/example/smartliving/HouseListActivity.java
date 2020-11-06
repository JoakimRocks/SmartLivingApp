package com.example.smartliving;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.smartliving.Interface.ItemClickListener;
import com.example.smartliving.Model.House;
import com.example.smartliving.ViewHolder.HouseViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class HouseListActivity extends AppCompatActivity {

    RecyclerView recycler_view;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference houseList;

    String categoryId="";

    FirebaseRecyclerAdapter<House, HouseViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_list);

        database = FirebaseDatabase.getInstance();
        houseList = database.getReference("House");

        recycler_view = (RecyclerView)findViewById(R.id.recycler_house);
        recycler_view.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recycler_view.setLayoutManager(layoutManager);

        if(getIntent() != null)
            categoryId = getIntent().getStringExtra("CategoryId");
        if(!categoryId.isEmpty() && categoryId != null){
            loadHouseList(categoryId);
        }

    }

    private void loadHouseList(String categoryId) {
        adapter = new FirebaseRecyclerAdapter<House, HouseViewHolder>(House.class, R.layout.house_item, HouseViewHolder.class,
                houseList.orderByChild("MenuId").equalTo(categoryId)){
            @Override
            protected void populateViewHolder(HouseViewHolder houseViewHolder, House house, int i) {
                    houseViewHolder.house_name.setText(house.getName());
                    Picasso.with(getBaseContext()).load(house.getImage())
                            .into(houseViewHolder.house_image);

                    final House local = house;
                    houseViewHolder.setItemClickListener(new ItemClickListener() {
                        @Override
                        public void onClick(View view, int position, boolean isLongClick) {
                            Intent houseDetail = new Intent(HouseListActivity.this, HouseDetailsActivity.class);
                            houseDetail.putExtra("HouseId",adapter.getRef(position).getKey());
                            startActivity(houseDetail);
                        }
                    });
            }
        };
        recycler_view.setAdapter(adapter);
    }
}
