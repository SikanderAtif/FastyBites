package com.example.fastybites;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MenuFragment extends Fragment {

    // Hooks (non-food related UI)
    RadioButton rb_subheading1_opt1, rb_subheading1_opt2, rb_subheading2_opt1, rb_subheading2_opt2;
    TextInputEditText tiet_textfield1, tiet_textfield2;
    Button btn_Clear, btn_Order;

    // New ListView + Adapter setup
    ListView lvFoodItems;
    ArrayList<FoodItem> foodList;
    FoodItemAdapter adapter;

    double Total = 0.0;

    public interface OnOrderPlacedListener {
        void onOrderPlaced(String name, String address, String orderType, String paymentMethod,
                           ArrayList<String> orderNames, ArrayList<Integer> orderQty, double total);
    }

    private OnOrderPlacedListener orderPlacedListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        orderPlacedListener = (OnOrderPlacedListener) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        // Initialize hooks
        init(view);

        // Set up food list
        lvFoodItems = view.findViewById(R.id.lv_food_items);
        foodList = new ArrayList<>();
        foodList.add(new FoodItem("Margherita Pizza", 12.99, R.drawable.fooditem1));
        foodList.add(new FoodItem("Classic Burger", 8.99, R.drawable.fooditem2));
        foodList.add(new FoodItem("Crispy Fries", 4.99, R.drawable.fooditem3));
        foodList.add(new FoodItem("Club Sandwich", 7.99, R.drawable.fooditem4));

        adapter = new FoodItemAdapter(requireContext(), foodList);
        lvFoodItems.setAdapter(adapter);

        FoodItemAdapter adapter = new FoodItemAdapter(requireContext(), foodList);
        lvFoodItems.setAdapter(adapter);

        lvFoodItems.post(() -> {
            int desiredItems = 4;
            View itemView = adapter.getView(0, null, lvFoodItems);
            itemView.measure(
                    View.MeasureSpec.makeMeasureSpec(lvFoodItems.getWidth(), View.MeasureSpec.EXACTLY),
                    View.MeasureSpec.UNSPECIFIED
            );
            int itemHeight = itemView.getMeasuredHeight();
            int totalHeight = itemHeight * desiredItems + (lvFoodItems.getDividerHeight() * (desiredItems - 1));
            lvFoodItems.getLayoutParams().height = totalHeight;
            lvFoodItems.requestLayout();
        });


        setupButtonLogic();

        return view;
    }

    private void setupButtonLogic() {

        rb_subheading1_opt1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                tiet_textfield2.setText("N/A");
                tiet_textfield2.setEnabled(false);
            }
        });

        rb_subheading1_opt2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                tiet_textfield2.setEnabled(true);
                tiet_textfield2.setText("");
            }
        });

        rb_subheading1_opt1.setChecked(true);
        rb_subheading2_opt1.setChecked(true);

        btn_Clear.setOnClickListener(v -> {
            View dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.confirmation_dialogue_design, null);
            Button btnYes = dialogView.findViewById(R.id.btnYes);
            Button btnNo = dialogView.findViewById(R.id.btnNo);

            AlertDialog dialog = new AlertDialog.Builder(requireContext())
                    .setView(dialogView)
                    .create();

            btnYes.setOnClickListener(innerview -> {
                // Reset form
                tiet_textfield1.setText("");
                if (rb_subheading1_opt2.isChecked()) {
                    tiet_textfield2.setText("");
                }
                rb_subheading1_opt1.setChecked(true);
                rb_subheading1_opt2.setChecked(false);
                rb_subheading2_opt1.setChecked(true);
                rb_subheading2_opt2.setChecked(false);

                // Reset adapter data
                for (FoodItem item : foodList) {
                    item.setQuantity(1);
                    item.setSelected(false);
                }
                adapter.notifyDataSetChanged();
                Total = 0.0;

                dialog.dismiss();
            });

            btnNo.setOnClickListener(innerview -> dialog.dismiss());
            dialog.show();
        });

        // Order button
        btn_Order.setOnClickListener(v -> {
            String name = Objects.requireNonNull(tiet_textfield1.getText()).toString().trim();
            String address = Objects.requireNonNull(tiet_textfield2.getText()).toString().trim();

            if (name.isEmpty()) {
                showAlert("Please Enter Your Name");
                return;
            }

            if (rb_subheading1_opt2.isChecked() && address.isEmpty()) {
                showAlert("Please Enter Delivery Address");
                return;
            }

            List<FoodItem> selectedItems = adapter.getSelectedItems();
            if (selectedItems.isEmpty()) {
                showAlert("Please Add At Least One Item");
                return;
            }

            String orderType = rb_subheading1_opt1.isChecked() ? "Pickup" : "Delivery";
            String paymentMethod = rb_subheading2_opt1.isChecked() ? "Cash" : "Card";

            ArrayList<String> orderNames = new ArrayList<>();
            ArrayList<Integer> orderQty = new ArrayList<>();
            Total = 0.0;

            for (FoodItem item : selectedItems) {
                orderNames.add(item.getName());
                orderQty.add(item.getQuantity());
                Total += item.getPrice() * item.getQuantity();
            }

            if (orderPlacedListener != null) {
                orderPlacedListener.onOrderPlaced(name, address, orderType, paymentMethod, orderNames, orderQty, Total);
            }
        });
    }

    private void showAlert(String message) {
        new AlertDialog.Builder(requireContext())
                .setTitle(message)
                .setNeutralButton("OK", (dialog, which) -> dialog.dismiss())
                .show();
    }

    private void init(View view) {
        tiet_textfield1 = view.findViewById(R.id.tiet_textfield1);
        tiet_textfield2 = view.findViewById(R.id.tiet_textfield2);
        btn_Clear = view.findViewById(R.id.btn_Clear);
        btn_Order = view.findViewById(R.id.btn_OrderNow);
        rb_subheading1_opt1 = view.findViewById(R.id.rb_subheading1_opt1);
        rb_subheading1_opt2 = view.findViewById(R.id.rb_subheading1_opt2);
        rb_subheading2_opt1 = view.findViewById(R.id.rb_subheading2_opt1);
        rb_subheading2_opt2 = view.findViewById(R.id.rb_subheading2_opt2);
    }
}
