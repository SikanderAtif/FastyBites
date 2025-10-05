package com.example.fastybites;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.security.KeyPair;
import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    int qty_item1 = 0, qty_item2 = 0, qty_item3 = 0, qty_item4 = 0;
    double Total = 0.0, price_item1, price_item2, price_item3, price_item4;

    //Hooks
    RadioButton rb_subheading1_opt1, rb_subheading1_opt2, rb_subheading2_opt1, rb_subheading2_opt2;
    TextView tv1_iv1,tv1_iv2,tv1_iv3,tv1_iv4;
    ImageButton btn_add1,btn_remove1,btn_add2,btn_remove2,btn_add3,btn_remove3,btn_add4,btn_remove4;
    TextInputEditText tiet_textfield1,tiet_textfield2,tiet_FoodItem1,tiet_FoodItem2,tiet_FoodItem3,tiet_FoodItem4;
    CheckBox cb1, cb2, cb3, cb4;
    Button btn_Clear, btn_Order;

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

        price_item1 = Double.parseDouble(getString(R.string.price_FoodItem1_Value));
        price_item2 = Double.parseDouble(getString(R.string.price_FoodItem2_Value));
        price_item3 = Double.parseDouble(getString(R.string.price_FoodItem3_Value));
        price_item4 = Double.parseDouble(getString(R.string.price_FoodItem4_Value));


        init();
        tv1_iv1.setSelected(true);
        tv1_iv2.setSelected(true);
        tv1_iv3.setSelected(true);
        tv1_iv4.setSelected(true);

        rb_subheading1_opt1.setOnClickListener((v) -> {
            if (rb_subheading1_opt1.isChecked()) {
                tiet_textfield2.setText("N/A");
            }
            else {
                tiet_textfield2.setText("");
            }
        });

        cb1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                tiet_FoodItem1.setText("1");
                qty_item1 = 1;
                Total = (price_item1*qty_item1) + (price_item2*qty_item2) + (price_item3*qty_item3) + (price_item4*qty_item4);
                btn_add1.setOnClickListener((v) -> {
                    if (Objects.requireNonNull(tiet_FoodItem1.getText()).toString().isEmpty()) {
                        tiet_FoodItem1.setText("0");
                        qty_item1 = 0;
                    }
                    if (tiet_FoodItem1.getText() != null) {
                        int num = Integer.parseInt(tiet_FoodItem1.getText().toString());
                        num++;
                        qty_item1 += 1;
                        tiet_FoodItem1.setText(String.valueOf(num));
                    }
                    Total = (price_item1*qty_item1) + (price_item2*qty_item2) + (price_item3*qty_item3) + (price_item4*qty_item4);
                });

                btn_remove1.setOnClickListener((v) -> {
                    if (Objects.requireNonNull(tiet_FoodItem1.getText()).toString().isEmpty()) {
                        tiet_FoodItem1.setText("0");
                        qty_item1 = 0;
                    }
                    if (tiet_FoodItem1.getText() != null && !tiet_FoodItem1.getText().toString().equals("1")) {
                        int num = Integer.parseInt(tiet_FoodItem1.getText().toString());
                        num--;
                        qty_item1 -= 1;
                        tiet_FoodItem1.setText(String.valueOf(num));
                    }
                    Total = (price_item1*qty_item1) + (price_item2*qty_item2) + (price_item3*qty_item3) + (price_item4*qty_item4);
                });
            }
            else {
                btn_add1.setOnClickListener(null);
                btn_remove1.setOnClickListener(null);
                tiet_FoodItem1.setText("0");
                qty_item1 = 0;
                Total = (price_item1*qty_item1) + (price_item2*qty_item2) + (price_item3*qty_item3) + (price_item4*qty_item4);
            }
        });

        cb2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                tiet_FoodItem2.setText("1");
                qty_item2 = 1;
                Total = (price_item1*qty_item1) + (price_item2*qty_item2) + (price_item3*qty_item3) + (price_item4*qty_item4);
                btn_add2.setOnClickListener((v) -> {
                    if (Objects.requireNonNull(tiet_FoodItem2.getText()).toString().isEmpty()) {
                        tiet_FoodItem2.setText("0");
                        qty_item2 = 0;
                    }
                    if (tiet_FoodItem2.getText() != null) {
                        int num = Integer.parseInt(tiet_FoodItem2.getText().toString());
                        num++;
                        qty_item2 += 1;
                        tiet_FoodItem2.setText(String.valueOf(num));
                    }
                    Total = (price_item1*qty_item1) + (price_item2*qty_item2) + (price_item3*qty_item3) + (price_item4*qty_item4);
                });

                btn_remove2.setOnClickListener((v) -> {
                    if (Objects.requireNonNull(tiet_FoodItem2.getText()).toString().isEmpty()) {
                        tiet_FoodItem2.setText("0");
                        qty_item2 = 0;
                    }
                    if (tiet_FoodItem2.getText() != null && !tiet_FoodItem2.getText().toString().equals("1")) {
                        int num = Integer.parseInt(tiet_FoodItem2.getText().toString());
                        num--;
                        qty_item2 -= 1;
                        tiet_FoodItem2.setText(String.valueOf(num));
                    }
                    Total = (price_item1*qty_item1) + (price_item2*qty_item2) + (price_item3*qty_item3) + (price_item4*qty_item4);
                });
            }
            else {
                btn_add2.setOnClickListener(null);
                btn_remove2.setOnClickListener(null);
                tiet_FoodItem2.setText("0");
                qty_item2 = 0;
                Total = (price_item1*qty_item1) + (price_item2*qty_item2) + (price_item3*qty_item3) + (price_item4*qty_item4);
            }
        });

        cb3.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                tiet_FoodItem3.setText("1");
                qty_item3 = 1;
                Total = (price_item1*qty_item1) + (price_item2*qty_item2) + (price_item3*qty_item3) + (price_item4*qty_item4);
                btn_add3.setOnClickListener((v) -> {
                    if (Objects.requireNonNull(tiet_FoodItem3.getText()).toString().isEmpty()) {
                        tiet_FoodItem3.setText("0");
                        qty_item3 = 0;
                    }
                    if (tiet_FoodItem3.getText() != null) {
                        int num = Integer.parseInt(tiet_FoodItem3.getText().toString());
                        num++;
                        qty_item3 += 1;
                        tiet_FoodItem3.setText(String.valueOf(num));
                    }
                    Total = (price_item1*qty_item1) + (price_item2*qty_item2) + (price_item3*qty_item3) + (price_item4*qty_item4);
                });

                btn_remove3.setOnClickListener((v) -> {
                    if (Objects.requireNonNull(tiet_FoodItem3.getText()).toString().isEmpty()) {
                        tiet_FoodItem3.setText("0");
                        qty_item3 = 0;
                    }
                    if (tiet_FoodItem3.getText() != null && !tiet_FoodItem3.getText().toString().equals("1")) {
                        int num = Integer.parseInt(tiet_FoodItem3.getText().toString());
                        num--;
                        qty_item3 -= 1;
                        tiet_FoodItem3.setText(String.valueOf(num));
                    }
                    Total = (price_item1*qty_item1) + (price_item2*qty_item2) + (price_item3*qty_item3) + (price_item4*qty_item4);
                });
            }
            else {
                btn_add3.setOnClickListener(null);
                btn_remove3.setOnClickListener(null);
                tiet_FoodItem3.setText("0");
                qty_item3 = 0;
                Total = (price_item1*qty_item1) + (price_item2*qty_item2) + (price_item3*qty_item3) + (price_item4*qty_item4);
            }
        });

        cb4.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                tiet_FoodItem4.setText("1");
                qty_item4 = 1;
                Total = (price_item1*qty_item1) + (price_item2*qty_item2) + (price_item3*qty_item3) + (price_item4*qty_item4);
                btn_add4.setOnClickListener((v) -> {
                    if (Objects.requireNonNull(tiet_FoodItem4.getText()).toString().isEmpty()) {
                        tiet_FoodItem4.setText("0");
                        qty_item4 = 0;
                    }
                    if (tiet_FoodItem4.getText() != null) {
                        int num = Integer.parseInt(tiet_FoodItem4.getText().toString());
                        num++;
                        qty_item4 += 1;
                        tiet_FoodItem4.setText(String.valueOf(num));
                    }
                    Total = (price_item1*qty_item1) + (price_item2*qty_item2) + (price_item3*qty_item3) + (price_item4*qty_item4);
                });

                btn_remove4.setOnClickListener((v) -> {
                    if (Objects.requireNonNull(tiet_FoodItem4.getText()).toString().isEmpty()) {
                        tiet_FoodItem4.setText("0");
                        qty_item4 = 0;
                    }
                    if (tiet_FoodItem4.getText() != null && !tiet_FoodItem4.getText().toString().equals("1")) {
                        int num = Integer.parseInt(tiet_FoodItem4.getText().toString());
                        num--;
                        qty_item4 -= 1;
                        tiet_FoodItem4.setText(String.valueOf(num));
                    }
                    Total = (price_item1*qty_item1) + (price_item2*qty_item2) + (price_item3*qty_item3) + (price_item4*qty_item4);
                });
            }
            else {
                btn_add4.setOnClickListener(null);
                btn_remove4.setOnClickListener(null);
                tiet_FoodItem4.setText("0");
                qty_item4 = 0;
                Total = (price_item1*qty_item1) + (price_item2*qty_item2) + (price_item3*qty_item3) + (price_item4*qty_item4);
            }
        });

        btn_Clear.setOnClickListener((v) -> {
            View dialogView = LayoutInflater.from(this).inflate(R.layout.confirmation_dialogue_design, null);

            Button btnYes, btnNo;
            btnYes = dialogView.findViewById(R.id.btnYes);
            btnNo = dialogView.findViewById(R.id.btnNo);

            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setView(dialogView);

            AlertDialog dialog = builder.create();
            dialog.show();

            btnYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cb1.setChecked(false);
                    cb2.setChecked(false);
                    cb3.setChecked(false);
                    cb4.setChecked(false);
                    tiet_textfield1.setText("");
                    tiet_textfield2.setText("");
                    tiet_FoodItem1.setText("0");
                    tiet_FoodItem2.setText("0");
                    tiet_FoodItem3.setText("0");
                    tiet_FoodItem4.setText("0");
                    price_item1 = 0;
                    price_item2 = 0;
                    price_item3 = 0;
                    price_item4 = 0;
                    qty_item1 = 0;
                    qty_item2 = 0;
                    qty_item3 = 0;
                    qty_item4 = 0;
                    Total = 0;
                    rb_subheading1_opt1.setChecked(false);
                    rb_subheading1_opt2.setChecked(false);
                    rb_subheading2_opt1.setChecked(false);
                    rb_subheading2_opt2.setChecked(false);

                    dialog.dismiss();
                }
            });

            btnNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        });

        btn_Order.setOnClickListener((v) -> {
            if (Objects.requireNonNull(tiet_textfield2.getText()).toString().isEmpty()) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setTitle("Please Enter Delivery Address")
                        .setNeutralButton(("OK"), (dialog, which) -> dialog.dismiss());

                AlertDialog dialog = builder.create();
                dialog.show();
            }
            else {
                String Name = Objects.requireNonNull(tiet_textfield1.getText()).toString().trim();
                String Address = Objects.requireNonNull(tiet_textfield2.getText()).toString().trim();
                String OrderType, PaymentMethod;
                if (rb_subheading1_opt1.isChecked()) {
                    OrderType = "Pickup";
                }
                else {
                    OrderType = "Delivery";
                }
                if (rb_subheading2_opt1.isChecked()) {
                    PaymentMethod = "Cash";
                }
                else {
                    PaymentMethod = "Card";
                }
                ArrayList<String> OrderNames = new ArrayList<>();
                ArrayList<Integer> OrderQty = new ArrayList<>();
                if (cb1.isChecked()) {
                    OrderNames.add("FoodItem1");
                    OrderQty.add(qty_item1);
                }
                if (cb2.isChecked()) {
                    OrderNames.add("FoodItem2");
                    OrderQty.add(qty_item2);
                }
                if (cb3.isChecked()) {
                    OrderNames.add("FoodItem3");
                    OrderQty.add(qty_item3);
                }
                if (cb4.isChecked()) {
                    OrderNames.add("FoodItem4");
                    OrderQty.add(qty_item4);
                }


                Intent intent = new Intent(MainActivity.this, SummaryScreen.class);
                intent.putExtra("key_name", Name);
                intent.putExtra("key_address", Address);
                intent.putExtra("key_ordertype", OrderType);
                intent.putExtra("key_paymentmethod", PaymentMethod);
                intent.putExtra("key_ordernames", OrderNames);
                intent.putExtra("key_orderqty", OrderQty);
                intent.putExtra("key_total", Total);
                startActivity(intent);
            }
        });

    }

    private void init() {
        tv1_iv1 = findViewById(R.id.tv1_iv1);
        tv1_iv2 = findViewById(R.id.tv1_iv2);
        tv1_iv3 = findViewById(R.id.tv1_iv3);
        tv1_iv4 = findViewById(R.id.tv1_iv4);
        btn_add1 = findViewById(R.id.ib_add1);
        btn_add2 = findViewById(R.id.ib_add2);
        btn_add3 = findViewById(R.id.ib_add3);
        btn_add4 = findViewById(R.id.ib_add4);
        btn_remove1 = findViewById(R.id.ib_remove1);
        btn_remove2 = findViewById(R.id.ib_remove2);
        btn_remove3 = findViewById(R.id.ib_remove3);
        btn_remove4 = findViewById(R.id.ib_remove4);
        tiet_FoodItem1 = findViewById(R.id.tiet_FoodItem1);
        tiet_FoodItem2 = findViewById(R.id.tiet_FoodItem2);
        tiet_FoodItem3 = findViewById(R.id.tiet_FoodItem3);
        tiet_FoodItem4 = findViewById(R.id.tiet_FoodItem4);
        tiet_textfield1 = findViewById(R.id.tiet_textfield1);
        tiet_textfield2 = findViewById(R.id.tiet_textfield2);
        cb1 = findViewById(R.id.cb1);
        cb2 = findViewById(R.id.cb2);
        cb3 = findViewById(R.id.cb3);
        cb4 = findViewById(R.id.cb4);
        btn_Clear = findViewById(R.id.btn_Clear);
        btn_Order = findViewById(R.id.btn_OrderNow);
        rb_subheading1_opt1 = findViewById(R.id.rb_subheading1_opt1);
        rb_subheading1_opt2 = findViewById(R.id.rb_subheading1_opt2);
        rb_subheading2_opt1 = findViewById(R.id.rb_subheading2_opt1);
        rb_subheading2_opt2 = findViewById(R.id.rb_subheading2_opt2);
    }
}