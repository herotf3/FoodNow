package com.example.asus.foodnow.Model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by user on 10/13/2016.
 */
public class Anh
{
    public String link;
    public String type;

    public Anh(JSONObject anh) {
        try {
            link=anh.getString("link");
            type=anh.getString("type");
        } catch (JSONException e) {
            link="";
            type="";
            e.printStackTrace();
        }
    }
}
