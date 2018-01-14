package com.example.asus.foodnow.Activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.foodnow.Interface.ItemClickListener;
import com.example.asus.foodnow.Model.Common;
import com.example.asus.foodnow.Model.Request;
import com.example.asus.foodnow.R;
import com.example.asus.foodnow.ViewHolder.OrderVH;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class OrderStatus extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<Request,OrderVH> adapter;

    FirebaseDatabase db;
    DatabaseReference refRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests_status);
        //firebase
        db=FirebaseDatabase.getInstance();
        refRequest=db.getReference().child("Requests");

        //list
        recyclerView=findViewById(R.id.rcl_listOrder);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        loadOrder(Common.currUser.getPhone());

    }

    private void loadOrder(String phone) {
        Query query=refRequest.orderByChild("phone").equalTo(phone);
        FirebaseRecyclerOptions<Request> options=
                new FirebaseRecyclerOptions.Builder<Request>()
                .setQuery(query,Request.class)
                .build();
        adapter=new FirebaseRecyclerAdapter<Request, OrderVH>(options) {
            @Override
            public OrderVH onCreateViewHolder(ViewGroup parent, int viewType) {
                View itemView= LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_order,parent,false);
                return new OrderVH(itemView);

            }
            @Override
            protected void onBindViewHolder(@NonNull OrderVH holder, int position, @NonNull Request model) {
                holder.tvOrderId.setText("#"+adapter.getRef(position).getKey());
                holder.tvOrderAddress.setText(model.getAddress());
                holder.tvOrderStatus.setText(convertCodeToStatus(model.getStatus()));
                holder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int pos, boolean isLongclick) {

                    }
                });
            }
        };
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }

    private String convertCodeToStatus(String status) {
        if (status.equals("0"))
            return "Đang chế biến";
        if (status.equals("1"))
            return "Shipping...";
        if (status.equals("2"))
            return "Đã giao!";
        return "Không xác định";
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
}
