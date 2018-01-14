package com.example.asus.foodnow.ViewHolder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.foodnow.Interface.ItemClickListener;
import com.example.asus.foodnow.Model.Food;
import com.example.asus.foodnow.Model.Supplier;
import com.example.asus.foodnow.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

/**
 * Created by ASUS on 1/5/2018.
 */

public class FoodVH extends RecyclerView.ViewHolder implements View.OnClickListener{
    private  TextView tvFoodName,tvCost,tvStore,tvAddrStore;
    private ImageView imgFood,statusLight;
    private Supplier supplier;


    ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public FoodVH(View itemView) {
        super(itemView);
        tvFoodName=itemView.findViewById(R.id.txt_list_food_name);
        tvCost=itemView.findViewById(R.id.item_food_cost);
        tvStore=itemView.findViewById(R.id.txt_food_store);
        tvAddrStore=itemView.findViewById(R.id.food_address);
        imgFood=itemView.findViewById(R.id.list_img_food);
        statusLight=itemView.findViewById(R.id.icon_stt);   //for avalable status

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }

    public void bindData(Food food, Context baseContext) {
        tvFoodName.setText(food.getName());
        tvCost.setText(food.getPrice());
        Picasso.with(baseContext).load(food.getImage()).into(imgFood);
        //set supplier info
        DatabaseReference supsRef= FirebaseDatabase.getInstance().getReference("Suppliers");
        supsRef.child(food.getSupplierId()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                supplier=dataSnapshot.getValue(Supplier.class);
                tvAddrStore.setText(supplier.getAddress());
                tvStore.setText(supplier.getName());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    public void bindQuickData() {

    }
}
