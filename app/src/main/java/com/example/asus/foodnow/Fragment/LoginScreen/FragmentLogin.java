package com.example.asus.foodnow.Fragment.LoginScreen;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.asus.foodnow.Fragment.SearchAndPick.FragmentMainSearch;
import com.example.asus.foodnow.Fragment.SearchAndPick.FragmentOrder;
import com.example.asus.foodnow.R;
/**
 * Created by ASUS on 12/2/2017.
 */

public class FragmentLogin extends Fragment {
    private View rootView;
    private Button login;
    private EditText username, password;
    private FragmentMainSearch frMain;

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_acc_login, null);
        InitView();
        Listener();
        return rootView;
    }

    public void Listener() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strUser,strPass;
                strUser=username.getText().toString();
                strPass=password.getText().toString();
               // if (strUser.length()>0 && strPass.length()>0)
                    //new LoginTask(getContext()).execute(strUser, strPass);
                frMain=new FragmentMainSearch();
                setFragment(frMain);
            }
        });
    }

    public void InitView() {
        login = (Button) rootView.findViewById(R.id.login_ok);
        username = (EditText) rootView.findViewById(R.id.login_username);
        password = (EditText) rootView.findViewById(R.id.login_password);

    }

    void setFragment(Fragment fragment){
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.myContainer,fragment,null)
                .addToBackStack(null)
                .commit();
    }
}
