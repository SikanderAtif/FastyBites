package com.example.fastybites;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import java.util.ArrayList;

public class SummaryAdapter extends BaseAdapter {

    private final Context context;
    private final ArrayList<SummaryItem> summaryItems;

    public SummaryAdapter(Context context, ArrayList<SummaryItem> summaryItems) {
        this.context = context;
        this.summaryItems = summaryItems;
    }

    @Override
    public int getCount() {
        return summaryItems.size();
    }

    @Override
    public Object getItem(int position) {
        return summaryItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_summary, parent, false);
        }

        TextView tvItem = convertView.findViewById(R.id.tv_ItemName);
        TextView tvQty = convertView.findViewById(R.id.tv_ItemQty);
        CardView qtyCard = convertView.findViewById(R.id.cv_ItemQty);

        SummaryItem item = summaryItems.get(position);

        tvItem.setText(item.getItemName());
        tvQty.setText("x" + item.getQuantity());

        return convertView;
    }
}
