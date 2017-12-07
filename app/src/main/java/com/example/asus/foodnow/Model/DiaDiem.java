package com.example.asus.foodnow.Model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by user on 9/23/2016.
 */

public class DiaDiem implements Serializable,Parcelable {
    private int id,luotCmt,luotView;
    private String ten;
    private String diaChi;
    private String gioMoCua;
    private String giaTien;
    private String gioiThieu;
    String luotAnh,luotChekIn;
    private double diem,lat,lng;
    private String urlImage;
    private int khuVucId,danhMucId;
    private KhuVuc khuVuc;
    private DanhMuc danhMuc;
    List<Anh> listAnh=new LinkedList<Anh>();
    //
    public DiaDiem(JSONObject object) {
        try {
            this.id = object.getInt("Id");
            this.ten=object.getString("Ten");
            this.luotCmt=object.getInt("luotComment");
            this.luotView=object.getInt("luotXem");
            this.diem=object.getDouble("diem");
            this.lat=object.getDouble("lat");
            this.lng=object.getDouble("lng");
            this.urlImage=object.getString("Anh");
            this.diaChi=object.getString("diachi");
            gioMoCua=object.getString("GioMoCua");
            giaTien=object.getString("GiaTien");
            gioiThieu=object.getString("GioiThieu");
            luotChekIn=object.getString("LuotCheckIn");
            luotAnh=object.getString("LuotAnh");
            //oject khu vuc
            this.khuVucId=object.getInt("KhuVucId");
            JSONObject JSkhuVuc=object.getJSONObject("KhuVuc");
            khuVuc=new KhuVuc();
            this.khuVuc.setId(JSkhuVuc.getInt("Id"));
            this.khuVuc.setSoLuong(JSkhuVuc.getString("Soluong"));
            this.khuVuc.setTen(JSkhuVuc.getString("Ten"));
            //oject danh muc
            this.danhMucId=object.getInt("DanhMucId");
            JSONObject JSdanhmuc=object.getJSONObject("DanhMuc");
            danhMuc=new DanhMuc();
            this.danhMuc.setTen(JSdanhmuc.getString("Ten"));
            this.danhMuc.setId(JSdanhmuc.getInt("Id"));
            //list images/videos
            JSONArray array=object.getJSONArray("Anhs");
            for (int i=0;i<array.length();i++){
                JSONObject jsObject=array.getJSONObject(i);
                Anh anh=new Anh(jsObject);
                listAnh.add(anh);
            }

        }catch (JSONException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //get & set

    protected DiaDiem(Parcel in) {
        id = in.readInt();
        luotCmt = in.readInt();
        luotView = in.readInt();
        ten = in.readString();
        diaChi = in.readString();
        gioMoCua = in.readString();
        giaTien = in.readString();
        gioiThieu = in.readString();
        luotAnh = in.readString();
        luotChekIn = in.readString();
        diem = in.readDouble();
        lat = in.readDouble();
        lng = in.readDouble();
        urlImage = in.readString();
        khuVucId = in.readInt();
        danhMucId = in.readInt();
    }

    public static final Creator<DiaDiem> CREATOR = new Creator<DiaDiem>() {
        @Override
        public DiaDiem createFromParcel(Parcel in) {
            return new DiaDiem(in);
        }

        @Override
        public DiaDiem[] newArray(int size) {
            return new DiaDiem[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLuotCmt() {
        return luotCmt;
    }

    public void setLuotCmt(int luotCmt) {
        this.luotCmt = luotCmt;
    }

    public int getLuotView() {
        return luotView;
    }

    public void setLuotView(int luotView) {
        this.luotView = luotView;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public double getDiem() {
        return diem;
    }

    public void setDiem(float diem) {
        this.diem = diem;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public int getKhuVucId() {
        return khuVucId;
    }

    public void setKhuVucId(int khuVucId) {
        this.khuVucId = khuVucId;
    }

    public int getDanhMucId() {
        return danhMucId;
    }

    public void setDanhMucId(int danhMucId) {
        this.danhMucId = danhMucId;
    }

    public KhuVuc getKhuVuc() {
        return khuVuc;
    }

    public void setKhuVuc(KhuVuc khuVuc) {
        this.khuVuc = khuVuc;
    }

    public DanhMuc getDanhMuc() {
        return danhMuc;
    }

    public void setDanhMuc(DanhMuc danhMuc) {
        this.danhMuc = danhMuc;
    }

    public String getGioMoCua() {
        return gioMoCua;
    }

    public void setGioMoCua(String gioMoCua) {
        this.gioMoCua = gioMoCua;
    }

    public String getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(String giaTien) {
        this.giaTien = giaTien;
    }

    public String getGioiThieu() {
        return gioiThieu;
    }

    public void setGioiThieu(String gioiThieu) {
        this.gioiThieu = gioiThieu;
    }

    public String getLuotAnh() {
        return luotAnh;
    }

    public void setLuotAnh(String luotAnh) {
        this.luotAnh = luotAnh;
    }

    public String getLuotChekIn() {
        return luotChekIn;
    }

    public void setLuotChekIn(String luotChekIn) {
        this.luotChekIn = luotChekIn;
    }

    public List<Anh> getListAnh() {
        return listAnh;
    }

    public void setListAnh(List<Anh> listAnh) {
        this.listAnh = listAnh;
    }

    public DiaDiem() {

    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(luotCmt);
        dest.writeInt(luotView);
        dest.writeString(ten);
        dest.writeString(diaChi);
        dest.writeString(gioMoCua);
        dest.writeString(giaTien);
        dest.writeString(gioiThieu);
        dest.writeString(luotAnh);
        dest.writeString(luotChekIn);
        dest.writeDouble(diem);
        dest.writeDouble(lat);
        dest.writeDouble(lng);
        dest.writeString(urlImage);
        dest.writeInt(khuVucId);
        dest.writeInt(danhMucId);
    }
}

