package com.example.asus.foodnow.Fragment.SearchAndPick;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;

import com.example.asus.foodnow.Model.Product;
import com.example.asus.foodnow.R;
import com.google.firebase.auth.FirebaseAuth;

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

    private void addListener() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Bạn có thật sự muốn đăng xuất?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseAuth.getInstance().signOut();
                        getActivity().getSupportFragmentManager().popBackStack();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                builder.setCancelable(true);
                AlertDialog alert = builder.create();
                alert.setTitle("Đăng xuất");
                alert.show();

            }
        });
        //chọn tìm thức ăn
        imgFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentListFood frFood=new FragmentListFood();
                Bundle b=new Bundle();
                b.putInt("type_choice",Product.TYPE_FOOD);
                frFood.setArguments(b);
                setFragment(frFood);
            }
        });
        //Chọn tìm đồ uống
        imgBeverage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentListFood frBev=new FragmentListFood();
                Bundle b=new Bundle();
                b.putInt("type_choice",Product.TYPE_BEVR);
                frBev.setArguments(b);
                setFragment(frBev);
            }
        });
    }

    private void setFragment(Fragment fragment){
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.myContainer,fragment)
                .addToBackStack("main_search")
                .commit();
    }

}
