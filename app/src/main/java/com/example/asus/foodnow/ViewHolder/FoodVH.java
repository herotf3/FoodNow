package com.example.asus.foodnow.ViewHolder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.foodnow.Interface.ItemClickListener;
import com.example.asus.foodnow.Model.Food;
import com.example.asus.foodnow.R;
import com.squareup.picasso.Picasso;

/**
 * Created by ASUS on 1/5/2018.
 */

public class FoodVH extends RecyclerView.ViewHolder implements View.OnClickListener{
    TextView tvFoodName,tvCost,tvStore,tvAddrStore;
    ImageView imgFood,statusLight;


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
        //set supplier info
        //..
        Picasso.with(baseContext).load(food.getImage()).into(imgFood);
    }
}
