package com.example.asus.foodnow.Fragment.LoginScreen;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.asus.foodnow.R;

/**
 * Created by nthanhphong on 9/21/2016.
 */

public class FragmentRegister extends Fragment {

    private View rootView;
    private Button register;
    private EditText username, password, name,repass;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_acc_signup, null);
        register = (Button) rootView.findViewById(R.id.register_ok);
        username = (EditText) rootView.findViewById(R.id.login_username);
        password = (EditText) rootView.findViewById(R.id.login_password);
        repass=(EditText)rootView.findViewById(R.id.login_re_password);
        name = (EditText) rootView.findViewById(R.id.login_name);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                builder1.setCancelable(true);
                AlertDialog alert11 = builder1.create();

                if (password.getText().toString().equals(repass.getText().toString())){
                    builder1.setMessage("Đăng ký thành công");
                    alert11.show();
                } else
                {
                    builder1.setMessage("Mật khẩu nhập lại không trùng khớp");
                    alert11.show();
                }

            }
        });

        return rootView;
    }

    /*class RegisterTask extends AsyncTask<String, String, Boolean> {
        private String username,pass,name;
        Context context;

        public RegisterTask(Context context, String username, String pass, String name) {
            this.context = context;
            this.username=username;
            this.pass=pass;
            this.name=name;
        }

        @Override
        protected Boolean doInBackground(String... data) {
            String responseString = null;
            try {
                URL url = new URL("http://103.237.147.137:9090/api/RegisterUser");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                //config server trả về data kiểu json
                conn.addRequestProperty("Accept", "text/json");
                conn.addRequestProperty("Content-Type","application/json");
                //config giao thức truyền lên server
                conn.setRequestMethod("POST");
                //---
                OutputStream outputStream=conn.getOutputStream();
                if (outputStream!=null) {
                    JSONObject root = new JSONObject();
                    root.put("Id", 1);
                    root.put("TenHienThi", name);
                    root.put("MatKhau", pass);
                    root.put("Email", username);
                    outputStream.write(root.toString().getBytes());
                    outputStream.flush();
                }
                //--
                conn.connect();
                if (conn.getResponseCode()==HttpURLConnection.HTTP_CREATED || conn.getResponseCode()==HttpURLConnection.HTTP_OK){
                    InputStream inputStream=conn.getInputStream();
                    BufferedReader buffReader=new BufferedReader(new InputStreamReader(inputStream));
                    StringBuffer stringBuff=new StringBuffer();
                    String line;
                    while ((line=buffReader.readLine())!=null){
                        stringBuff.append(line);
                    }
                    responseString=stringBuff.toString();
                    if (responseString.equals("1"))
                        return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
            if (result) {
                builder1.setMessage("Đăng ký thành công");
            }
            else
                builder1.setMessage("Đăng ký thất bại, vui lòng thử lại");
            builder1.setCancelable(true);
            AlertDialog alert11 = builder1.create();
            alert11.show();
        }
    }
    */
}
