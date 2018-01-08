package com.example.asus.foodnow.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.asus.foodnow.Interface.OnOrderSelect;
import com.example.asus.foodnow.Model.Drink;
import com.example.asus.foodnow.Model.Food2;
import com.example.asus.foodnow.R;

import java.util.List;

/**
 * Created by ASUS on 12/8/2017.
 */
//Lớp quản lý giao diện từng thẻ món ăn

public class AdapterListFood extends RecyclerView.Adapter<FoodViewHD> {
    private OnOrderSelect onOrderListenr;
    List<Food2> foodList;
    List<Drink> drinkList;
    LayoutInflater inflater;

    public AdapterListFood(Context context, OnOrderSelect onOrderListenr, List<Food2> foods, List<Drink> drinks) {
        this.onOrderListenr = onOrderListenr;
        this.foodList = foods;
        this.drinkList=drinks;
        inflater=LayoutInflater.from(context);
    }

    public AdapterListFood(Context context, OnOrderSelect onOrderListenr, List<Drink> drinkList) {
        this.onOrderListenr = onOrderListenr;
        this.drinkList = drinkList;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public FoodViewHD onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FoodViewHD(inflater.inflate(R.layout.item_a_food,parent,false), onOrderListenr);
    }

    @Override
    public void onBindViewHolder(FoodViewHD holder, int position) {
        holder.setData(foodList.get(position));
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }
}
