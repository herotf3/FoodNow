package com.example.asus.foodnow.Fragment.SearchAndPick;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.example.asus.foodnow.Adapter.OnOrderSelect;
import com.example.asus.foodnow.Model.Food;
import com.example.asus.foodnow.R;
import com.example.asus.foodnow.UICustomView.ImageExpanable;

/**
 * Created by ASUS on 12/7/2017.
 */

public class FragmentListFood extends Fragment implements OnOrderSelect {
    View root;
    ImageExpanable imgFood;
    boolean zoom=false;
    final ScaleAnimation growAnim = new ScaleAnimation(1.0f, 1.0f, 1.0f, 1.15f);
    final ScaleAnimation shrinkAnim = new ScaleAnimation(1.0f, 1.0f, 1.15f, 1.0f);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root=inflater.inflate(R.layout.fragment_list_food,null);
        InitView();
        //AddListener();
        return root;
    }

    private void AddListener() {

    }

    private void InitView() {
        imgFood=(ImageExpanable) root.findViewById(R.id.list_img_food);
    }


    @Override
    public void onOrderFoodCallback(Food food) {

    }
}

