package com.example.asus.foodnow.Activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asus.foodnow.Adapter.AdapterFoods;
import com.example.asus.foodnow.Interface.ItemClickListener;
import com.example.asus.foodnow.Model.Food;
import com.example.asus.foodnow.R;
import com.example.asus.foodnow.ViewHolder.FoodVH;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mancj.materialsearchbar.MaterialSearchBar;

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
    //Search functionally
    FirebaseRecyclerAdapter<Food,FoodVH> searchAdapter;
    List<String> suggestList=new ArrayList<>();
    MaterialSearchBar materialSearchBar;

    FirebaseDatabase db;
    DatabaseReference foodsTable;
    String categoryId;
    private List<Food> listFood=new ArrayList<Food>();
    public static List<String> listKey=new ArrayList<String>();
    View view;
    TextView tvEmpty;

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
        view=this.findViewById(R.id.background_listFood);
        tvEmpty=findViewById(R.id.noFood_message);

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
        //search
        materialSearchBar=findViewById(R.id.searchBar);
        loadSuggest();
        materialSearchBar.setLastSuggestions(suggestList);
        materialSearchBar.setCardViewElevation(5);
        materialSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // change suggest list when user type in search bar
                List<String> suggest=new ArrayList<>();
                for (String search:suggestList){
                    if (search.toLowerCase().contains(materialSearchBar.getText().toString().toLowerCase()))
                        suggest.add(search);
                }
                materialSearchBar.setLastSuggestions(suggest);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        materialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                //search bar close -> restore orinal suggest list
                if (!enabled)
                    recyclerView.setAdapter(adapter);
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                //when search finish -> display suggest list
                startSearch(text);
            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });
    }

    private void startSearch(CharSequence text) {
        Query query=foodsTable.orderByChild("Name").equalTo(text.toString());
        FirebaseRecyclerOptions<Food> options=new FirebaseRecyclerOptions.Builder<Food>()
                .setQuery(query,Food.class).build();

        searchAdapter=new FirebaseRecyclerAdapter<Food, FoodVH>(options) {
            @Override
            public FoodVH onCreateViewHolder(ViewGroup parent, int viewType) {
                View itemView=LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_a_food,parent,false);
                return new FoodVH(itemView);
            }

            @Override
            protected void onBindViewHolder(@NonNull FoodVH holder, int position, @NonNull Food model) {
                holder.bindData(model,getBaseContext());
                holder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int pos, boolean isLongclick) {
                        Intent intent=new Intent(ListFood.this, FoodDetail.class);
                        intent.putExtra("FoodId",searchAdapter.getRef(pos).getKey());
                        startActivity(intent);
                    }
                });
            }
        };
        searchAdapter.startListening();
        recyclerView.setAdapter(searchAdapter);
    }

    private void loadSuggest() {
        foodsTable.orderByChild("MenuId").equalTo(categoryId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot postSnapshot:dataSnapshot.getChildren()){
                            Food item=postSnapshot.getValue(Food.class);
                            suggestList.add(item.getName());
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
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
                if (adapter.getItemCount()==0)
                {
                    view.setBackgroundColor(getResources().getColor(R.color.second_color));
                    tvEmpty.setVisibility(View.VISIBLE);
                }else{
                    view.setBackgroundColor(getResources().getColor(R.color.cardview_dark_background));
                    tvEmpty.setVisibility(View.GONE);
                }
            }


        };
        recyclerView.setAdapter(adapter);
    }
}
