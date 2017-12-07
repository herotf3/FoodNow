package com.example.asus.foodnow.Model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by user on 10/6/2016.
 */

public class Catalog {
    static final String KEY_ID="Id";
    static final String KEY_TEN="Ten";
    public int id;
    public String ten;
    public String urlImage;
    public Catalog(JSONObject jsObject)
    {
        try {
            this.id=jsObject.getInt(KEY_ID);
            this.ten=jsObject.getString(KEY_TEN);
        } catch (JSONException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Catalog() {
    }
}
