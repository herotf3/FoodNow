package com.example.asus.foodnow.Activity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.foodnow.Adapter.CartAdapter;
import com.example.asus.foodnow.Database.Database;
import com.example.asus.foodnow.Model.Common;
import com.example.asus.foodnow.Model.Order;
import com.example.asus.foodnow.Model.Request;
import com.example.asus.foodnow.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Cart extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    TextView tvTotal;
    Button btnPlaceOrder;

    FirebaseDatabase db;
    DatabaseReference requestRef;

    List<Order> orders =new ArrayList<>();
    CartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        //firebase
        db=FirebaseDatabase.getInstance();
        requestRef=db.getReference("Requests");

        //Init view
        recyclerView=findViewById(R.id.rcl_list_cart);
        recyclerView.setHasFixedSize(true);
        layoutManager= new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        tvTotal=findViewById(R.id.tvTotal);
        btnPlaceOrder=findViewById(R.id.btn_placeOrder);
        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //yeu cau nhap dia chi
                AlertDialog.Builder builder=new AlertDialog.Builder(Cart.this);
                builder.setTitle("Chỉ một bước nữa");
                builder.setMessage("Nhập địa chỉ giao hàng");
                final EditText edtAddress=new EditText(Cart.this);
                LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                edtAddress.setLayoutParams(lp);

                builder.setView(edtAddress);
                builder.setIcon(R.drawable.icon_home);
                builder.setPositiveButton("Xong", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Request request=new Request(Common.currUser.getName(),
                                edtAddress.getText().toString(),
                                Common.currUser.getPhone(),tvTotal.getText().toString(),
                                orders);
                        //add to firebase
                        requestRef.child(String.valueOf(System.currentTimeMillis()))
                                .setValue(request);
                        //delete cart in sql db
                        new Database(Cart.this).cleanCart();
                        Toast.makeText(getApplicationContext(),"Cảm ơn bạn đã đặt món <3",Toast.LENGTH_LONG).show();
                        finish();
                    }
                });

                builder.setNegativeButton("Quay lại", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.show();


            }
        });
        loadListRequest();

    }

    private void loadListRequest() {
        orders =new Database(this).getCart();
        adapter=new CartAdapter(orders,this);
        recyclerView.setAdapter(adapter);

        //tinh tong tien
        int total=0;
        for (Order order: orders){
            total+=Integer.parseInt(order.getPrice())*Integer.parseInt(order.getQuantity());
        }
        //format currency
        Locale locale=new Locale("vi","VN");
        NumberFormat fmt=NumberFormat.getCurrencyInstance(locale);
        tvTotal.setText(fmt.format(total));
    }
}
