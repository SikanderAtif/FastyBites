package com.example.fastybites;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FoodItemAdapter extends BaseAdapter {

    private final Context context;
    private final ArrayList<FoodItem> foodItems;

    public FoodItemAdapter(Context context, ArrayList<FoodItem> foodItems) {
        this.context = context;
        this.foodItems = foodItems;
    }

    @Override
    public int getCount() {
        return foodItems.size();
    }

    @Override
    public Object getItem(int position) {
        return foodItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("DefaultLocale")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FoodItem item = foodItems.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_food, parent, false);
        }

        CheckBox cbSelect = convertView.findViewById(R.id.cb_select);
        ImageView ivFood = convertView.findViewById(R.id.iv_food);
        TextView tvName = convertView.findViewById(R.id.tv_food_name);
        TextView tvPrice = convertView.findViewById(R.id.tv_food_price);
        TextView tvQty = convertView.findViewById(R.id.tv_food_qty);
        ImageButton btnAdd = convertView.findViewById(R.id.ib_add);
        ImageButton btnRemove = convertView.findViewById(R.id.ib_remove);

        tvName.setText(item.getName());
        tvName.setSelected(true);
        tvPrice.setText(String.format("$%.2f", item.getPrice()));
        ivFood.setImageResource(item.getImageResId());
        tvQty.setText(String.valueOf(item.getQuantity()));
        cbSelect.setChecked(item.isSelected());

        // ✅ Checkbox selection handling
        cbSelect.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                item.setSelected(isChecked);
                item.setQuantity(1);
                tvQty.setText("1");
            } else {
                item.setSelected(isChecked);
                item.setQuantity(0);
                tvQty.setText("0");
            }
        });

        // ✅ Increment quantity
        btnAdd.setOnClickListener(v -> {
            if (item.isSelected()) {
                int qty = item.getQuantity() + 1;
                item.setQuantity(qty);
                tvQty.setText(String.valueOf(qty));
            }
        });

        // ✅ Decrement quantity
        btnRemove.setOnClickListener(v -> {
            int qty = item.getQuantity();
            if (qty > 0) {
                qty--;
                item.setQuantity(qty);
                tvQty.setText(String.valueOf(qty));
            }
        });

        return convertView;
    }

    // ✅ Get selected items
    public ArrayList<FoodItem> getSelectedItems() {
        ArrayList<FoodItem> selected = new ArrayList<>();
        for (FoodItem item : foodItems) {
            if (item.isSelected() && item.getQuantity() > 0) {
                selected.add(item);
            }
        }
        return selected;
    }
}