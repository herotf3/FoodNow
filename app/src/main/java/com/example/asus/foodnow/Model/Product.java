package com.example.asus.foodnow.Model;

import android.graphics.drawable.Drawable;

/**
 * Created by ASUS on 12/9/2017.
 */

public class Product {
    int id;
    String name, point;
    Double cost;
    Drawable img;
    int idStore;

    public static final int TYPE_FOOD=1;
    public static final int TYPE_BEVR=2;

    public Store getSupllier() {
        return supllier;
    }

    public void setSupllier(Store supllier) {
        this.supllier = supllier;
    }

    Store supllier;



    public Product(int id, String name, String point, Double cost, Drawable img, int idStore) {
        this.id = id;
        this.name = name;
        this.point = point;
        this.cost = cost;
        this.img = img;
        this.idStore = idStore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Drawable getImg() {
        return img;
    }

    public void setImg(Drawable img) {
        this.img = img;
    }

    public int getIdStore() {
        return idStore;
    }

    public void setIdStore(int idStore) {
        this.idStore = idStore;
    }

}
