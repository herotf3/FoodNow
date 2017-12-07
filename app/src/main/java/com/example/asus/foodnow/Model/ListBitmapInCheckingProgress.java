package com.example.asus.foodnow.Model;

import android.graphics.Bitmap;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by user on 10/21/2016.
 */

public class ListBitmapInCheckingProgress {
    public List<Bitmap> listBitmap;
    public static ListBitmapInCheckingProgress thisInstance;
    private List<Bitmap> savedInstanceState;

    private ListBitmapInCheckingProgress(){
        listBitmap=new LinkedList<Bitmap>();
    }

    public  static ListBitmapInCheckingProgress getInstance(){
        if (thisInstance==null)
            thisInstance=new ListBitmapInCheckingProgress();
        return thisInstance;
    }

    public void savingInstanceState(){
        savedInstanceState=new LinkedList<Bitmap>();
        savedInstanceState.addAll(listBitmap);
    }

    public void resetToPreviousState()
    {
        if (savedInstanceState!=null) {
            if (listBitmap != null)
                listBitmap.clear();
            listBitmap.addAll(savedInstanceState);
        }
    }

    public void addBitmap(Bitmap bitmap){
        if (!listBitmap.contains(bitmap))
            listBitmap.add(bitmap);
    }
}
