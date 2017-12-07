package com.example.asus.foodnow.Model;

/**
 * Created by user on 9/23/2016.
 */
public class KhuVuc {
    private int id;
    private String ten,soLuong;

    public KhuVuc(int id, String ten, String soLuong) {
        this.id = id;
        this.ten = ten;
        this.soLuong = soLuong;
    }

    public KhuVuc() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }
}
