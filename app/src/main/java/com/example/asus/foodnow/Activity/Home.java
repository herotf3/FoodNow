package com.example.asus.foodnow.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asus.foodnow.Interface.ItemClickListener;
import com.example.asus.foodnow.Model.Category;
import com.example.asus.foodnow.Model.Common;
import com.example.asus.foodnow.R;
import com.example.asus.foodnow.ViewHolder.CategoryVH;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView tvUserName;

    FirebaseDatabase db;
    DatabaseReference categoryTable;

    RecyclerView recyclerMenu;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter adapter;
    Query query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu_side);
        toolbar.setOverflowIcon(ContextCompat.getDrawable(this,R.drawable.ic_menu_side));

        setSupportActionBar(toolbar);

        //init firebase
        db=FirebaseDatabase.getInstance();
        categoryTable=db.getReference().child("Category");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cartIntent=new Intent(Home.this,Cart.class);
                startActivity(cartIntent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //set name user
        View header=navigationView.getHeaderView(0);
        tvUserName=header.findViewById(R.id.tvUserName);
        if (Common.currUser!=null){
            tvUserName.setText(Common.currUser.getName());
        }else
            tvUserName.setText(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());

            FirebaseAuth auth=FirebaseAuth.getInstance();



        //Load menu category
        recyclerMenu=(RecyclerView) findViewById(R.id.rcl_menu);
        recyclerMenu.setHasFixedSize(true);
        layoutManager=new GridLayoutManager(this,2);
        recyclerMenu.setLayoutManager(layoutManager);

        loadMenu();
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
        FirebaseAuth auth=FirebaseAuth.getInstance();
        auth.signOut();
    }

    private void loadMenu() {

        query = categoryTable.limitToLast(50);
        FirebaseRecyclerOptions<Category> options =
                new FirebaseRecyclerOptions.Builder<Category>()
                        .setQuery(query, Category.class)
                        .build();
        adapter= new FirebaseRecyclerAdapter<Category, CategoryVH>(options) {
            @Override
            public CategoryVH onCreateViewHolder(ViewGroup parent, int viewType) {
                View itemView= LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.category_item,parent,false);

                return new CategoryVH(itemView);
            }

            @Override
            protected void onBindViewHolder(@NonNull CategoryVH holder, final int position, @NonNull Category model) {
                //bind data
                holder.binData(model,getBaseContext());

                final Category clickItem=model;
                holder.setItemListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int pos, boolean isLongclick) {
                        //user click a category
                        //get this category id to send to new ListFood Activity
                        Intent intentFoods=new Intent(Home.this,ListFood.class);
                        intentFoods.putExtra("CategoryId",adapter.getRef(pos).getKey());
                        startActivity(intentFoods);
                    }
                });
            }
        };
        recyclerMenu.setAdapter(adapter);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id){
            case R.id.nav_cart:
                break;
            case R.id.nav_menu:
                break;
            case R.id.nav_orders:
                break;
            case R.id.nav_signOut:
                break;
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
