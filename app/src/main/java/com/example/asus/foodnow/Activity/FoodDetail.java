package com.example.asus.foodnow.Activity;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.asus.foodnow.Database.Database;
import com.example.asus.foodnow.Model.Food;
import com.example.asus.foodnow.Model.Order;
import com.example.asus.foodnow.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class FoodDetail extends AppCompatActivity {
    TextView tvFood,tvDescription,tvPrice;
    ImageView imgFood;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton fabCart;
    ElegantNumberButton btnNum;

    String FoodId="";
    Food currFood;
    FirebaseDatabase db;
    DatabaseReference refFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        //firebase
        db=FirebaseDatabase.getInstance();
        refFood=db.getReference("Foods");
        //init View
        tvFood=findViewById(R.id.detail_foodName);
        tvDescription=findViewById(R.id.detail_description);
        tvPrice=findViewById(R.id.detail_foodPrice);
        imgFood=findViewById(R.id.detail_imgFood);
        collapsingToolbarLayout=findViewById(R.id.collapseLayout);
        fabCart=findViewById(R.id.detail_fab_cart);
        btnNum=findViewById(R.id.detail_numberBtn);


        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsAppBar);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);

        //get intent
        if (getIntent()!=null){
            FoodId=getIntent().getStringExtra("FoodId");
        }
        if (!FoodId.isEmpty()){
            getDetailFood();
        }

        //listener
        fabCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Order order=new Order(FoodId,currFood.getName(),btnNum.getNumber(),currFood.getPrice(),currFood.getDiscount());
                new Database(getBaseContext()).addToCart(order);
                Toast.makeText(getBaseContext(),"Đã thêm vào giỏ hàng",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getDetailFood() {
        refFood.child(FoodId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                currFood=dataSnapshot.getValue(Food.class);
                //bind data
                Picasso.with(getBaseContext()).load(currFood.getImage()).into(imgFood);
                collapsingToolbarLayout.setTitle(currFood.getName());
                tvFood.setText(currFood.getName());
                tvDescription.setText(currFood.getDescription());
                tvPrice.setText(currFood.getPrice());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
