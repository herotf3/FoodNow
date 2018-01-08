package com.example.asus.foodnow.Model;

/**
 * Created by ASUS on 1/5/2018.
 */

public class Category {
    String name, image;

    public Category(){}

    public Category(String name, String imageUri) {
        this.name = name;
        this.image = imageUri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
