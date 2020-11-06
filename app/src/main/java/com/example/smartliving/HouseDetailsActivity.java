package com.example.smartliving;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartliving.Model.House;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class HouseDetailsActivity extends AppCompatActivity {
    private ImageView imageView1,imageView2,imageView3,imageView4,imageView5;
    private TextView image1,image2,image3,image4,image5;

    TextView houseName,housePrice,houseDescription;
    CollapsingToolbarLayout collapsingToolbarLayout;

    FloatingActionButton btnCall;

    String houseId= "";
    FirebaseDatabase database;
    DatabaseReference houses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_details);

        database = FirebaseDatabase.getInstance();
        houses = database.getReference("Rooms");

        btnCall = (FloatingActionButton)findViewById(R.id.btn_call);

        houseName = (TextView)findViewById(R.id.house_name);
        housePrice = (TextView)findViewById(R.id.house_price);
        houseDescription = (TextView)findViewById(R.id.house_description);

        imageView1 = (ImageView)findViewById(R.id.img_house_1);
        imageView2 = (ImageView)findViewById(R.id.img_house_2);
        imageView3 = (ImageView)findViewById(R.id.img_house_3);
        imageView4 = (ImageView)findViewById(R.id.img_house_4);
        imageView5 = (ImageView)findViewById(R.id.img_house_5);

        image1 = (TextView)findViewById(R.id.img1);
        image2 = (TextView)findViewById(R.id.img2);
        image3 = (TextView)findViewById(R.id.img3);
        image4 = (TextView)findViewById(R.id.img4);
        image5 = (TextView)findViewById(R.id.img5);


        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar);

        if(getIntent() != null)
            houseId = getIntent().getStringExtra("HouseId");
        if(!houseId.isEmpty()){
            getHouseDetail(houseId);
        }

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent call = new Intent(Intent.ACTION_CALL);
                call.setData(Uri.parse("tel:0714779098"));
                startActivity(call);

            }
        });

    }

    private void getHouseDetail(String houseId) {

        houses.child(houseId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                House house = dataSnapshot.getValue(House.class);

                Picasso.with(getBaseContext()).load(house.getImage1())
                        .into(imageView1);
                Picasso.with(getBaseContext()).load(house.getImage2())
                        .into(imageView2);
                Picasso.with(getBaseContext()).load(house.getImage3())
                        .into(imageView3);
                Picasso.with(getBaseContext()).load(house.getImage4())
                        .into(imageView4);
                Picasso.with(getBaseContext()).load(house.getImage5())
                        .into(imageView5);

                housePrice.setText(house.getRent());
                houseName.setText(house.getName1());
                houseName.setText(house.getName2());
                houseName.setText(house.getName3());
                houseName.setText(house.getName4());
                houseName.setText(house.getName5());
                houseDescription.setText(house.getDescription());


                image1.setText(house.getName1());
                image2.setText(house.getName2());
                image3.setText(house.getName3());
                image4.setText(house.getName4());
                image5.setText(house.getName5());

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
