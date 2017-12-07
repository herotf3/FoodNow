package com.example.asus.foodnow.Fragment.LoginScreen;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.foodnow.R;

/**
 * Created by user on 10/1/2016.
 */

public class FragmentTabLogin extends Fragment {
    TabLayout tabLogins;
    Fragment fragmentLogin;
    FragmentRegister fragmentRegister;
    View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_acc_tab,container,false);
        init();
        setListenerChangeTab();
        setupTabLayout();
        return rootView;
    }

    private void setListenerChangeTab() {
        tabLogins.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setCurrentTab(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setCurrentTab(int tabPos) {
        if (tabPos==0)
            replaceFragment(fragmentLogin);
        else
            replaceFragment(fragmentRegister);
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.login_tab_frame, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }

    private void setupTabLayout() {
        tabLogins.addTab(tabLogins.newTab().setText("Đăng nhập"),true);
        tabLogins.addTab(tabLogins.newTab().setText("Đăng ký"));
        tabLogins.setTabTextColors(getResources().getColor(R.color.tab_login_color),Color.WHITE);
    }

    private void init() {
        tabLogins= (TabLayout) rootView.findViewById(R.id.login_tab);
        fragmentLogin=new FragmentLogin();
        fragmentRegister=new FragmentRegister();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
