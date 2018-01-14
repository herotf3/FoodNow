package com.example.asus.foodnow.Activity;

import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.asus.foodnow.Database.Database;
import com.example.asus.foodnow.Model.Food;
import com.example.asus.foodnow.Model.Order;
import com.example.asus.foodnow.Model.Supplier;
import com.example.asus.foodnow.R;
import com.example.asus.foodnow.ViewHolder.FoodVH;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.Locale;

public class FoodDetail extends AppCompatActivity {
    TextView tvFood,tvDescription,tvPrice;
    ImageView imgFood;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton fabCart;
    ElegantNumberButton btnNum;
    //supplier zone
    ImageView imgSup;
    TextView tvSupName,tvSupAddress,tvSupShipCost,tvDistance;
    RatingBar rateBar;
    Supplier supplier;
    RecyclerView recyclerView;
    FirebaseRecyclerAdapter<Food,FoodVH> adapterQuickAdd;

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

        tvSupName=findViewById(R.id.supplier_name);
        tvSupAddress=findViewById(R.id.supplier_address);
        tvSupShipCost=findViewById(R.id.supplier_shipCost);
        tvDistance=findViewById(R.id.supplier_distance);
        rateBar=findViewById(R.id.rate_bar);
        imgSup=findViewById(R.id.supplier_img);

        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsAppBar);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);

        //get intent
        if (getIntent()!=null){
            FoodId=getIntent().getStringExtra("FoodId");
        }
        if (!FoodId.isEmpty()){
            setDisplayData();
        }

        //listener
        fabCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Order order=new Order(FoodId,currFood.getName(),btnNum.getNumber(),currFood.getPrice(),currFood.getDiscount());
                order.setSupplierId(currFood.getSupplierId());
                new Database(getBaseContext()).addToCart(order);
                Toast.makeText(getBaseContext(),"Đã thêm vào giỏ hàng",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setDisplayData() {
        refFood.child(FoodId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                currFood=dataSnapshot.getValue(Food.class);
                //bind data
                Picasso.with(getBaseContext()).load(currFood.getImage()).into(imgFood);
                tvFood.setText(currFood.getName());
                tvDescription.setText(currFood.getDescription());
                tvPrice.setText(currFood.getPrice());
                //supplier zone
                final DatabaseReference supRef=FirebaseDatabase.getInstance().getReference("Suppliers");
                supRef.child(currFood.getSupplierId()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        supplier=dataSnapshot.getValue(Supplier.class);
                        tvSupName.setText(supplier.getName());
                        collapsingToolbarLayout.setTitle(supplier.getName());
                        tvSupAddress.setText(supplier.getAddress());
                        Locale locale=new Locale("vi","VN");
                        NumberFormat fmt=NumberFormat.getCurrencyInstance(locale);
                        tvSupShipCost.setText(fmt.format(supplier.getShipCost()));
                        rateBar.setMax(5);
                        rateBar.setNumStars(supplier.getRating());
                        setQuickAddMenu(supplier);
                    }

                    private void setQuickAddMenu(Supplier supplier) {
                        recyclerView=findViewById(R.id.list_quickAdd);
                        recyclerView.setHasFixedSize(true);
                        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getBaseContext());
                        recyclerView.setLayoutManager(layoutManager);

                        Query query=refFood.orderByChild("supplierId").equalTo(currFood.getSupplierId());
                        FirebaseRecyclerOptions<Food> options=new FirebaseRecyclerOptions.Builder<Food>()
                                .setQuery(query,Food.class).build();
                        adapterQuickAdd=new FirebaseRecyclerAdapter<Food, FoodVH>(options) {
                            @Override
                            protected void onBindViewHolder(@NonNull FoodVH holder, int position, @NonNull Food model) {
                                holder.bindQuickData();
                            }

                            @Override
                            public FoodVH onCreateViewHolder(ViewGroup parent, int viewType) {
                                return null;
                            }
                        };

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
