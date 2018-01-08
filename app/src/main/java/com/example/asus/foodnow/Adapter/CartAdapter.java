package com.example.asus.foodnow.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.asus.foodnow.Activity.Cart;
import com.example.asus.foodnow.Interface.ItemClickListener;
import com.example.asus.foodnow.Model.Order;
import com.example.asus.foodnow.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by ASUS on 1/8/2018.
 */

class CartVH extends RecyclerView.ViewHolder implements View.OnClickListener{

    TextView tvCartName,  tvPrice;
    ImageView imgCount;

    private ItemClickListener itemClickListener;

    public CartVH(View itemView) {
        super(itemView);
        tvCartName=itemView.findViewById(R.id.cart_itemName);
        tvPrice=itemView.findViewById(R.id.cart_itemPrice);
        imgCount=itemView.findViewById(R.id.cart_itemCount);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }
}

public class CartAdapter extends RecyclerView.Adapter<CartVH> {
    List<Order> orderList=new ArrayList<Order>();
    Context context;

    public CartAdapter(List<Order> orderList, Context context) {
        this.orderList = orderList;
        this.context = context;
    }

    @Override
    public CartVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.item_cart,parent,false);
        return new CartVH(view);
    }

    @Override
    public void onBindViewHolder(CartVH holder, int position) {
        TextDrawable drawable=TextDrawable.builder()
                .buildRound(""+orderList.get(position).getQuantity(), Color.RED);
        holder.imgCount.setImageDrawable(drawable);

        Locale locale=new Locale("vi","VN");
        NumberFormat fmt=NumberFormat.getCurrencyInstance(locale);
        Order order=orderList.get(position);
        int price=(Integer.parseInt(order.getPrice()))*(Integer.parseInt(order.getQuantity()));
        holder.tvPrice.setText(fmt.format(price));
        holder.tvCartName.setText(order.getProductName());
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }
}
