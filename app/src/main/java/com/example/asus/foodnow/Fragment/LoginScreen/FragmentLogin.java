package com.example.asus.foodnow.Fragment.LoginScreen;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.asus.foodnow.Activity.Home;
import com.example.asus.foodnow.Fragment.SearchAndPick.FragmentMainSearch;
import com.example.asus.foodnow.Model.Common;
import com.example.asus.foodnow.Model.User;
import com.example.asus.foodnow.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.QuerySnapshot;

import javax.xml.datatype.Duration;

import static android.content.ContentValues.TAG;

/**
 * Created by ASUS on 12/2/2017.
 */

public class FragmentLogin extends Fragment {
    private static final int RC_SIGN_IN = 1;
    private View rootView;
    private Button login;
    private ImageButton btnGplus;
    private EditText email, password;
    private FragmentMainSearch frMain;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private GoogleApiClient googleApiClient;
// ...


    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_acc_login, null);
        InitView();
        Listener();
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser!=null)
            updateUI(currentUser);
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    private void updateUI(FirebaseUser currentUser) {
        //go to main menu activity
        Intent intent=new Intent(getContext(), Home.class);
        DatabaseReference userRef=FirebaseDatabase.getInstance().getReference("Users");
        //select current user with uid in user table
        userRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user=dataSnapshot.child(firebaseAuth.getCurrentUser().getUid()).getValue(User.class);
                //save current user instance
                Common.currUser=user;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        startActivity(intent);
    }

    public void Listener() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strEmail,strPass;
                strEmail= email.getText().toString();
                strPass=password.getText().toString();
                progressDialog.setMessage("Verifying...");
                progressDialog.show();
               if (strEmail.length()>0 && strPass.length()>0)
               {
                   progressDialog.dismiss();
                   firebaseAuth.signInWithEmailAndPassword(strEmail,strPass)
                           .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                               @Override
                               public void onComplete(@NonNull Task<AuthResult> task) {
                                   if (task.isSuccessful()){
                                       Toast.makeText(getContext(),"Ăn ngay thôi...",Toast.LENGTH_LONG).show();
                                       FirebaseUser user =firebaseAuth.getCurrentUser();
                                       updateUI(user);
                                   }
                                   else
                                       Toast.makeText(getContext(),task.getException().getMessage().toString(),Toast.LENGTH_LONG).show();
                               }
                           });
               }else{
                   Toast.makeText(getContext(),"Vui lòng điền đầy đủ thông tin.",Toast.LENGTH_LONG).show();
               }

            }
        });

        btnGplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInGG();
            }
        });
    }

    private void signInGG() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);

    }

    public void InitView() {
        login = (Button) rootView.findViewById(R.id.login_ok);
        email = (EditText) rootView.findViewById(R.id.signin_email);
        password = (EditText) rootView.findViewById(R.id.signin_pass);
        btnGplus=(ImageButton)rootView.findViewById(R.id.btn_gplus);
        progressDialog=new ProgressDialog(getContext());
        firebaseAuth=FirebaseAuth.getInstance();

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        if (googleApiClient==null)
            googleApiClient=new GoogleApiClient.Builder(getContext().getApplicationContext())
                .enableAutoManage(getActivity(), new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Toast.makeText(getContext(),"Có lỗi xảy ra!", Toast.LENGTH_LONG);

                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso).build();

    }

    void setFragment(Fragment fragment){
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.myContainer,fragment,null)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // ...
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            Toast.makeText(getContext(),"Đăng nhập thành công!",Toast.LENGTH_SHORT).show();
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getContext(),"Authentication Failed", Toast.LENGTH_LONG).show();

                        }

                        // ...
                    }
                });

    }

    @Override
    public void onPause() {
        super.onPause();
        googleApiClient.stopAutoManage(getActivity());
        googleApiClient.disconnect();
    }

}
