package com.example.asus.foodnow.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.foodnow.Adapter.AdapterFoods;
import com.example.asus.foodnow.Interface.ItemClickListener;
import com.example.asus.foodnow.Model.Food;
import com.example.asus.foodnow.R;
import com.example.asus.foodnow.ViewHolder.FoodVH;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListFood extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<Food,FoodVH> adapter;
    Query query;
    AdapterFoods adapterFoods;

    FirebaseDatabase db;
    DatabaseReference foodsTable;
    String categoryId;
    private List<Food> listFood=new ArrayList<Food>();
    public static List<String> listKey=new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_food);

        //Firebase
        db=FirebaseDatabase.getInstance();
        foodsTable=db.getReference().child("Foods");

        recyclerView=findViewById(R.id.rcl_foodList);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

//        adapterFoods=new AdapterFoods(this,listFood);
//        recyclerView.setAdapter(adapterFoods);

        getListData();

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    private void getListData() {
        Intent intent=getIntent();
        if (intent!=null){
            categoryId=intent.getStringExtra("CategoryId");
        }
        if (!categoryId.isEmpty() && categoryId!=null){
            loadListFood();
        }

    }

    private void loadListFood() {
        //Select * from foodTable where MenuId=categoryId
        query = foodsTable.orderByChild("MenuId").equalTo(categoryId);
        FirebaseRecyclerOptions<Food> options =
                new FirebaseRecyclerOptions.Builder<Food>()
                        .setQuery(query, Food.class)
                        .build();
        adapter=new FirebaseRecyclerAdapter<Food, FoodVH>(options) {
            @Override
            public FoodVH onCreateViewHolder(ViewGroup parent, int viewType) {
                View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_a_food,parent,false);
                return new FoodVH(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull FoodVH holder, int position, @NonNull Food model) {
                holder.bindData(model,getBaseContext());
                holder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int pos, boolean isLongclick) {
                        Intent intent=new Intent(ListFood.this, FoodDetail.class);
                        intent.putExtra("FoodId",adapter.getRef(pos).getKey());
                        startActivity(intent);
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);


    }

    private void fetchData(DataSnapshot snapshot) {

        Map<String, Object> td = (HashMap<String,Object>) snapshot.getValue();
        Food food=new Food((String)td.get("Name"),(String)td.get("Image"),(String)td.get("Description"),(String)td.get("Price"),
                (String)td.get("Discount"),(String)td.get("MenuId"));
        listKey.add(snapshot.getKey());
        listFood.add(food);

    }
}
