package com.example.asus.foodnow.Model;

/**
 * Created by ASUS on 1/7/2018.
 */

public class Common {
    public static User currUser;
    public static String convertCodeToStatus(String status) {
        if (status.equals("0"))
            return "Đang chế biến";
        if (status.equals("1"))
            return "Shipping...";
        if (status.equals("2"))
            return "Đã giao!";
        return "Không xác định";
    }
}
