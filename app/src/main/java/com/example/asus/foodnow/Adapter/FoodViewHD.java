package com.example.asus.foodnow.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.foodnow.Interface.OnOrderSelect;
import com.example.asus.foodnow.Model.Product;
import com.example.asus.foodnow.R;

/**
 * Created by ASUS on 12/8/2017.
 */

class FoodViewHD extends RecyclerView.ViewHolder{

    TextView tv_food,tv_store,tv_cost, tv_point;
    Button btn_view_food, btn_order_food;
    ImageView imgFood;
    OnOrderSelect onOrderSelect;

    public FoodViewHD(View itemView) {
        super(itemView);
    }

    public FoodViewHD(View itemView, OnOrderSelect onOrderSelect) {
        super(itemView);
        tv_food=(TextView)itemView.findViewById(R.id.txt_list_food_name);
        tv_store=(TextView)itemView.findViewById(R.id.txt_food_store);


        imgFood=(ImageView)itemView.findViewById(R.id.list_img_food);
        this.onOrderSelect = onOrderSelect;
    }

    public void setData(final Product food){
        tv_food.setText(food.getName());
        tv_store.setText(food.getSupllier().getName());
        tv_cost.setText(String.valueOf(food.getCost()));
        tv_point.setText(food.getPoint());
        imgFood.setImageDrawable(food.getImg());
        btn_view_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onOrderSelect.onOrderFoodCallback(food);
            }
        });
    }


}
