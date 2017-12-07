package com.example.asus.foodnow.Model;

/**
 * Created by user on 9/23/2016.
 */
public class DanhMuc {
    private int id;
    private String ten;

    public DanhMuc(int id, String ten) {
        this.id = id;
        this.ten = ten;
    }

    public DanhMuc() {

    }

    public int getId() {
        return id;
    }

    public String getTen() {
        return ten;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
}
