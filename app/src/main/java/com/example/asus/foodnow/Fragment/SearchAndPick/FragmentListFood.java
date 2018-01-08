package com.example.asus.foodnow.Fragment.SearchAndPick;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.asus.foodnow.Interface.OnOrderSelect;
import com.example.asus.foodnow.Model.Product;
import com.example.asus.foodnow.R;

import java.util.List;

/**
 * Created by ASUS on 12/7/2017.
 */

public class FragmentListFood extends Fragment implements OnOrderSelect {
    View root;
    TextView tvPageType;
    Spinner spCatalog;
    ImageButton ibtnBack;
    RecyclerView rvList;

    private int type;  //food or beverage
    List<Product> listProduct;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root=inflater.inflate(R.layout.fragment_list_food,null);
        InitView();
        SetData();
        AddListener();
        return root;
    }

    private void SetData() {
        Bundle b=this.getArguments();
        type=b.getInt("type_choice");
        if (type==Product.TYPE_FOOD)
            tvPageType.setText("Món ăn");
        else
            tvPageType.setText("Đồ uống");
    }


    private void AddListener() {
        ibtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

    }

    private void InitView() {
        tvPageType=(TextView)root.findViewById(R.id.tv_food_or_drink);
        spCatalog=(Spinner)root.findViewById(R.id.list_food_catalog);
        rvList=(RecyclerView)root.findViewById(R.id.rv_list_product);
        ibtnBack=(ImageButton)root.findViewById(R.id.btn_backto_main);
    }

    @Override
    public void onOrderFoodCallback(Product food) {
        int storeId=food.getIdStore();
    }
}

