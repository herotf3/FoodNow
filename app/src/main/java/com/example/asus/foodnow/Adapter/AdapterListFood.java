package com.example.asus.foodnow.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.asus.foodnow.Model.DiaDiem;
import com.example.asus.foodnow.Model.Food;
import com.example.asus.foodnow.Model.Store;
import com.example.asus.foodnow.R;

import java.util.List;

/**
 * Created by ASUS on 12/8/2017.
 */
//Lớp quản lý giao diện từng thẻ món ăn

public class AdapterListFood extends RecyclerView.Adapter<FoodViewHD> {
    private OnOrderSelect onOrderSelect;
    List<Food> foodList;
    LayoutInflater inflater;

    public AdapterListFood(Context context,OnOrderSelect onOrderSelect, List<Food> storeList) {
        this.onOrderSelect = onOrderSelect;
        this.foodList = storeList;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public FoodViewHD onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FoodViewHD(inflater.inflate(R.layout.item_a_food,parent,false),onOrderSelect);
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
