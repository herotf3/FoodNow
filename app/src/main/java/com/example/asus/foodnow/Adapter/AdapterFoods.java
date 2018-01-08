package com.example.asus.foodnow.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.foodnow.Activity.ListFood;
import com.example.asus.foodnow.Activity.FoodDetail;
import com.example.asus.foodnow.Interface.ItemClickListener;
import com.example.asus.foodnow.Model.Food;
import com.example.asus.foodnow.R;
import com.example.asus.foodnow.ViewHolder.FoodVH;

import java.util.List;

/**
 * Created by ASUS on 1/6/2018.
 */

public class AdapterFoods extends RecyclerView.Adapter<FoodVH> {
    Context context;
    List<Food> listFood;

    public AdapterFoods(Context context,List<Food> foods){
        this.context=context;
        listFood=foods;
    }

    @Override
    public FoodVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.item_a_food,parent,false);
        return new FoodVH(view);
    }

    @Override
    public void onBindViewHolder(FoodVH holder, int position) {
        holder.bindData(listFood.get(position),context);
        //set listener when user click a food card
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int pos, boolean isLongclick) {
                Intent intent=new Intent(context,FoodDetail.class);
                String foodId=ListFood.listKey.get(pos);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listFood.size();
    }
}
