package com.example.asus.foodnow.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.example.asus.foodnow.Model.Order;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 1/7/2018.
 */

public class Database extends SQLiteAssetHelper {
    private static final String DB_NAME="OrderFood.db";
    private static final int VER=1;

    public Database(Context context) {
        super(context, DB_NAME,null, VER);
    }

    public List<Order> getCart(){
        SQLiteDatabase db=getReadableDatabase();
        SQLiteQueryBuilder qb=new SQLiteQueryBuilder();

        String[] select={"ProductId","ProductName","Quantity","Price","Discount"};
        String table="OrderDetail";

        qb.setTables(table);
        Cursor c=qb.query(db,select,null,null,null,null,null);
        final List<Order> res=new ArrayList<Order>();
        if (c.moveToFirst())
        do{
            res.add(new Order(c.getString(c.getColumnIndex("ProductId")),
                    c.getString(c.getColumnIndex("ProductName")),
                    c.getString(c.getColumnIndex("Quantity")),
                    c.getString(c.getColumnIndex("Price")),
                    c.getString(c.getColumnIndex("Discount")) )
            );
        }while (c.moveToNext());
        return res;
    }

    public void addToCart(Order order){
        SQLiteDatabase db= getReadableDatabase();
        String query=String.format("INSERT INTO OrderDetail(ProductId,ProductName,Quantity,Price,Discount) VALUES ('%s','%s','%s','%s','%s');",
                order.getProductId(),
                order.getProductName(),
                order.getQuantity(),
                order.getPrice(),
                order.getDiscount()
        );
        db.execSQL(query);
    }

    public void cleanCart(){
        SQLiteDatabase db=getReadableDatabase();
        String query=String.format("DELETE FROM OrderDetail");
        db.execSQL(query);
    }

}
