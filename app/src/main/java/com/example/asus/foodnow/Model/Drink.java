package com.example.asus.foodnow.Model;

import android.graphics.drawable.Drawable;

/**
 * Created by ASUS on 12/9/2017.
 */

public class Drink {

    public Drink(int id, String name, String point, Double cost, Drawable img, int sup_store) {
        this.id = id;
        this.name = name;
        this.point = point;
        this.cost = cost;
        this.img = img;
        this.sup_store = sup_store;
    }

    public Drink(int id, String name, String point, Double cost, Drawable img) {
        this.id = id;
        this.name = name;
        this.point = point;
        this.cost = cost;
        this.img = img;
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

    public int getSup_store() {
        return sup_store;
    }

    public void setSup_store(int sup_store) {
        this.sup_store = sup_store;
    }

    int id;     //PK
    String name, point;
    Double cost;
    Drawable img;
    int sup_store;  //FK
}
