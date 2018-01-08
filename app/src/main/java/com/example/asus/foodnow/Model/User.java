package com.example.asus.foodnow.Model;

/**
 * Created by ASUS on 1/7/2018.
 */

public class User {
    private String Name,Email,Phone;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public User() {

    }

    public User(String name, String email, String phone) {

        Name = name;
        Email = email;
        Phone = phone;
    }
}
