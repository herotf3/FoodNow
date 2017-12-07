package com.example.asus.foodnow.Fragment.SearchAndPick;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;

import com.example.asus.foodnow.R;

/**
 * Created by ASUS on 12/6/2017.
 */

public class FragmentMainSearch extends Fragment {
    View rootView;
    SearchView searchView;
    EditText searchBox;
    ImageButton btnBack, btnAccInfo;
    ImageView imgFood,imgBeverage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_main_search,null);
        initView();
        addListener();
        return rootView;
    }

    private void initView(){
        searchBox=(EditText)rootView.findViewById(R.id.search_box);
        searchView=(SearchView)rootView.findViewById(R.id.search_view);
        btnBack=rootView.findViewById(R.id.btn_backto_login);
        btnAccInfo=rootView.findViewById(R.id.btn_acc_info);
        imgFood=rootView.findViewById(R.id.search_img_food);
        imgBeverage=rootView.findViewById(R.id.search_img_beverage);
    }

    private void addListener(){
        btnBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }
}
