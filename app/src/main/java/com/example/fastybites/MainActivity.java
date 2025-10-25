package com.example.fastybites;

import android.os.Bundle;

import android.util.Log;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MenuFragment.OnOrderPlacedListener {
    private static final String TAG = "MainActivity"; // Or any descriptive tag
    public void onOrderPlaced(String name, String address, String orderType, String paymentMethod, ArrayList<String> orderNames, ArrayList<Integer> orderQty, double total) {

        Log.d(TAG, "Name: " + name);
        Log.d(TAG, "Address: " + address);
        Log.d(TAG, "Order Type: " + orderType);
        Log.d(TAG, "Payment Method: " + paymentMethod);
        Log.d(TAG, "Order Names: " + orderNames);
        Log.d(TAG, "Order Quantity: " + orderQty);
        Log.d(TAG, "Total: " + total);

        SummaryFragment summaryFragment = new SummaryFragment();
        Bundle bundle = new Bundle();
        bundle.putString("key_name", name);
        bundle.putString("key_address", address);
        bundle.putString("key_ordertype", orderType);
        bundle.putString("key_paymentmethod", paymentMethod);
        bundle.putStringArrayList("key_ordernames", orderNames);
        bundle.putIntegerArrayList("key_orderqty", orderQty);
        bundle.putDouble("key_total", total);
        summaryFragment.setArguments(bundle);
        
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, summaryFragment)
                .addToBackStack(null)
                .commit();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new MenuFragment())
                    .commit();
        }
    }
}