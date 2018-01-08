package com.example.asus.foodnow.Fragment.LoginScreen;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asus.foodnow.Model.User;
import com.example.asus.foodnow.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by nthanhphong on 9/21/2016.
 */

public class FragmentRegister extends Fragment {

    private View rootView;
    private Button register;
    private EditText username, password, edtName,edtPhone,repass;
    private String email,pass,phone, name;

    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_acc_signup, null);
        register = (Button) rootView.findViewById(R.id.register_ok);
        username = (EditText) rootView.findViewById(R.id.signup_email);
        password = (EditText) rootView.findViewById(R.id.signup_password);
        repass=(EditText)rootView.findViewById(R.id.login_re_password);
        edtName = (EditText) rootView.findViewById(R.id.login_name);
        edtPhone=(EditText)rootView.findViewById(R.id.signup_phone);

        progressDialog=new ProgressDialog(this.getContext());
        firebaseAuth=FirebaseAuth.getInstance();

        SetListener();

        return rootView;
    }

    private void SetListener() {
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email=username.getText().toString();
                pass=password.getText().toString();
                if (validateInput()){
                    progressDialog.setMessage("Đang thực hiện...");
                    progressDialog.show();
                    //crete account
                    firebaseAuth.createUserWithEmailAndPassword(email,pass)
                            .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressDialog.dismiss();
                                    if (task.isSuccessful()) {
                                        addUser2DB();
                                        Toast.makeText(getContext(), "Đăng ký thành công!", Toast.LENGTH_LONG).show();
                                    }
                                    else{
                                        Toast.makeText(getContext(),"Đăng ký thất bại!\n"+task.getException().toString(), Toast.LENGTH_LONG).show();
                                    }
                                }
                            });


                } else
                {
                    Toast.makeText(getContext(),"Mật khẩu nhập lại không khớp!",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void addUser2DB() {
        FirebaseDatabase db=FirebaseDatabase.getInstance();
        final DatabaseReference userRef=db.getReference("Users"); //table of users
        final String phone=edtPhone.getText().toString();
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(phone).exists()){
                    progressDialog.dismiss();
                    Toast.makeText(getContext(),"Số điện thoại này đã đăng ký rồi!",Toast.LENGTH_LONG).show();
                }else{
                    progressDialog.dismiss();
                    User user=new User(edtName.getText().toString(),email,edtPhone.getText().toString());
                    //add to table with uid as primarykey
                    userRef.child(firebaseAuth.getCurrentUser().getUid()).setValue(user);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private boolean validateInput() {
        if (! password.getText().toString().equals(repass.getText().toString()) )
                return false;
        return true;
    }


}
