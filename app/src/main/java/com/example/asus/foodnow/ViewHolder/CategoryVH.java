package com.example.asus.foodnow.ViewHolder;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.foodnow.Interface.ItemClickListener;
import com.example.asus.foodnow.Model.Category;
import com.example.asus.foodnow.R;
import com.squareup.picasso.Picasso;

/**
 * Created by ASUS on 1/5/2018.
 */

public class CategoryVH extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView tvName;
    public ImageView imgView;

    private ItemClickListener itemListener;

    public CategoryVH(View itemView) {
        super(itemView);
        tvName=(TextView) itemView.findViewById(R.id.tvCategory);
        imgView=(ImageView) itemView.findViewById(R.id.img_category);

        itemView.setOnClickListener(this);
    }

    public void setItemListener(ItemClickListener itemListener) {
        this.itemListener = itemListener;
    }

    @Override
    public void onClick(View view) {
        itemListener.onClick(view,getAdapterPosition(),false);
    }

    public void binData(Category model, Context baseContext) {
        tvName.setText(model.getName());
        Typeface face=Typeface.createFromAsset(baseContext.getAssets(),"font/Nabila.ttf");
        tvName.setTypeface(face);
        Picasso.with(baseContext).load(model.getImage()).into(imgView);
    }
}
