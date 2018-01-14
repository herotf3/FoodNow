package com.example.asus.foodnow.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.asus.foodnow.Interface.ItemClickListener;
import com.example.asus.foodnow.R;

/**
 * Created by ASUS on 1/12/2018.
 */

public class OrderVH extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView tvOrderId,tvOrderStatus,tvOrderAddress;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    private ItemClickListener itemClickListener;

    public OrderVH(View itemView) {
        super(itemView);
        tvOrderId=(TextView) itemView.findViewById(R.id.order_id);
        tvOrderAddress=(TextView)itemView.findViewById(R.id.tv_orderAddress);
        tvOrderStatus=(TextView)itemView.findViewById(R.id.order_status);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }

}
