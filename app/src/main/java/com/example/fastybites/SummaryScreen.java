package com.example.fastybites;


import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.util.Pair;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class SummaryScreen extends AppCompatActivity {

    String Receipt;
    TextView tv_name, tv_orderType, tv_address, tv_paymentMethod, tv_orderName1, tv_orderQty1, tv_orderName2, tv_orderQty2, tv_orderName3, tv_orderQty3, tv_orderName4, tv_orderQty4, tv_totalBill;
    RelativeLayout rl_SummaryItem1, rl_SummaryItem2, rl_SummaryItem3, rl_SummaryItem4;
    Button btnShareOrder, btnDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_summary_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String Name = extras.getString("key_name");
            String OrderType = extras.getString("key_ordertype");
            String Address = extras.getString("key_address");
            String PaymentMethod = extras.getString("key_paymentmethod");
            ArrayList<String> OrderNames = extras.getStringArrayList("key_ordernames");
            ArrayList<Integer> OrderQty = extras.getIntegerArrayList("key_orderqty");

            tv_name.setText(Name);
            tv_orderType.setText(OrderType);
            tv_address.setText(Address);
            tv_paymentMethod.setText(PaymentMethod);
            Receipt = "Name: " + Name + "\n" + "Order Type: " + OrderType + "\n" + "Address: " + Address + "\n" + "Payment Method: " + PaymentMethod + "\n" + "Order Details:\n" ;

            if (OrderNames != null && OrderQty != null) {
                if (OrderNames.contains("FoodItem1")) {
                    rl_SummaryItem1.setVisibility(VISIBLE);
                    tv_orderName1.setText(getString(R.string.FoodItem1));
                    int index = OrderNames.indexOf("FoodItem1");
                    String qty = "x"+ OrderQty.get(index);
                    tv_orderQty1.setText(qty);
                    Receipt += tv_orderName1.getText() + " " + qty + "\n";
                }
                if (OrderNames.contains("FoodItem2")) {
                    rl_SummaryItem2.setVisibility(VISIBLE);
                    tv_orderName2.setText(getString(R.string.FoodItem2));
                    int index = OrderNames.indexOf("FoodItem2");
                    String qty = "x"+ OrderQty.get(index);
                    tv_orderQty2.setText(qty);
                    Receipt += tv_orderName2.getText() + " " + qty + "\n";
                }
                if (OrderNames.contains("FoodItem3")) {
                    rl_SummaryItem3.setVisibility(VISIBLE);
                    tv_orderName3.setText(getString(R.string.FoodItem3));
                    int index = OrderNames.indexOf("FoodItem3");
                    String qty = "x"+ OrderQty.get(index);
                    tv_orderQty3.setText(qty);
                    Receipt += tv_orderName3.getText() + " " + qty + "\n";
                }
                if (OrderNames.contains("FoodItem4")) {
                    rl_SummaryItem4.setVisibility(VISIBLE);
                    tv_orderName4.setText(getString(R.string.FoodItem4));
                    int index = OrderNames.indexOf("FoodItem4");
                    String qty = "x"+ OrderQty.get(index);
                    tv_orderQty4.setText(qty);
                    Receipt += tv_orderName4.getText() + " " + qty + "\n";
                }
            }

            String bill = "Rs. " + extras.getDouble("key_total");
            tv_totalBill.setText(bill);
            Receipt += "\nTotal: " + bill;
        }

        btnShareOrder.setOnClickListener((v) -> {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, Receipt);
            sendIntent.setType("text/plain");

            Intent shareIntent = Intent.createChooser(sendIntent, "Share your order via");
            startActivity(shareIntent);
        });

        btnDone.setOnClickListener((v) -> {
            finish();
        });
    }

    private void init() {
        tv_name = findViewById(R.id.tv_Name);
        tv_orderType = findViewById(R.id.tv_OrderType);
        tv_address = findViewById(R.id.tv_Address);
        tv_paymentMethod = findViewById(R.id.tv_PaymentMethod);
        tv_orderName1 = findViewById(R.id.tv_Item1);
        tv_orderQty1 = findViewById(R.id.tv_CountItem1);
        tv_orderName2 = findViewById(R.id.tv_Item2);
        tv_orderQty2 = findViewById(R.id.tv_CountItem2);
        tv_orderName3 = findViewById(R.id.tv_Item3);
        tv_orderQty3 = findViewById(R.id.tv_CountItem3);
        tv_orderName4 = findViewById(R.id.tv_Item4);
        tv_orderQty4 = findViewById(R.id.tv_CountItem4);
        tv_totalBill = findViewById(R.id.tv_TotalBill);
        rl_SummaryItem1 = findViewById(R.id.rl_SummaryItem1);
        rl_SummaryItem2 = findViewById(R.id.rl_SummaryItem2);
        rl_SummaryItem3 = findViewById(R.id.rl_SummaryItem3);
        rl_SummaryItem4 = findViewById(R.id.rl_SummaryItem4);
        btnShareOrder = findViewById(R.id.btnShareOrder);
        btnDone = findViewById(R.id.btnDone);
        rl_SummaryItem1.setVisibility(GONE);
        rl_SummaryItem2.setVisibility(GONE);
        rl_SummaryItem3.setVisibility(GONE);
        rl_SummaryItem4.setVisibility(GONE);
    }
}