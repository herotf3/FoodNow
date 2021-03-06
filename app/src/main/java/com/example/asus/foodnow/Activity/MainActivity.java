package com.example.asus.foodnow.Activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.asus.foodnow.Fragment.SearchAndPick.FragmentListFood;
import com.example.asus.foodnow.Fragment.SearchAndPick.FragmentMainSearch;
import com.example.asus.foodnow.Fragment.SearchAndPick.FragmentOrder;
import com.example.asus.foodnow.Fragment.LoginScreen.FragmentTabLogin;
import com.example.asus.foodnow.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState==null){
            FragmentTabLogin frLogin=new FragmentTabLogin();
            FragmentOrder frOrder=new FragmentOrder();
            FragmentListFood frListFood=new FragmentListFood();
            setFragment(frLogin);

        }
    }
    void setFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.myContainer,fragment,"home")
                .commit();
    }
}
