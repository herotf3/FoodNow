package com.example.asus.foodnow.Model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by user on 11/2/2016.
 */

public class BoSuuTapDiaDiem {
    private String ten,soDiaDiem,linkAnh;

    public String getTen() {
        return ten;
    }

    public String getSoDiaDiem() {
        return soDiaDiem;
    }

    public String getLinkAnh() {
        return linkAnh;
    }

    public BoSuuTapDiaDiem(JSONObject object) {
        if (object!=null){
            try {
                ten=object.getString("Ten");
                soDiaDiem=object.getString("Soluongdiem");
                linkAnh=object.getString("Anh");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
