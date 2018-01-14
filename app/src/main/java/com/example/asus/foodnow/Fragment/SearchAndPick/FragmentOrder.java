package com.example.asus.foodnow.Fragment.SearchAndPick;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.foodnow.Model.Supplier;
import com.example.asus.foodnow.R;

/**
 * Created by user on 10/11/2016.
 */

public class FragmentOrder extends Fragment{
    View rootView;
    TextView tvHeaderStore;
    ImageButton btnCmt,btnSave,btnBack;
    Button btn2Cart, btn2Purchase;
    ImageView imageFood;
    TextView address,info,costRange;
    Supplier store;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_order_instore,null);
        //initView();
        //setData();
        //setListener();
        return rootView;
    }

    private void setData() {
        store=(Supplier) this.getArguments().getSerializable("diaDiemDuocChon");
        //if (imageFood!=null)
        //    new DownloadHinhTask(imageFood).execute(thisDiaDiem.getUrlImage());
        tvHeaderStore.setText(store.getName());
        address.setText(store.getAddress());

        //collection


    }

    private void initView() {
        tvHeaderStore=(TextView)rootView.findViewById(R.id.tvHeaderStoreDetail);
        btnBack=(ImageButton) rootView.findViewById(R.id.btnBack);
        btnCmt=(ImageButton) rootView.findViewById(R.id.btn_cmt);
        btnSave=(ImageButton) rootView.findViewById(R.id.btn_save_diaDiem);
        imageFood = (ImageView) rootView.findViewById(R.id.order_food_image);
        btn2Cart=(Button)rootView.findViewById(R.id.btn_view_cart);
        btn2Purchase=(Button)rootView.findViewById(R.id.btn_purchase);

    }

    private void setListener() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }

    void replaceFragment(Fragment fragment){
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.myContainer,fragment,null).addToBackStack(null).commit();
    }
}
