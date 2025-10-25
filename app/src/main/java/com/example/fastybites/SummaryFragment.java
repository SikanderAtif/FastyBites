package com.example.fastybites;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class SummaryFragment extends Fragment {

    private TextView tv_name, tv_orderType, tv_address, tv_paymentMethod, tv_totalBill;
    private Button btnShareOrder, btnDone;
    private ListView lv_summaryItems;

    private String Receipt = "";

    private void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) return;

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(
                    View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.UNSPECIFIED
            );
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_summary, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(view);

        Bundle extras = getArguments();
        if (extras != null) {
            String name = extras.getString("key_name");
            String orderType = extras.getString("key_ordertype");
            String address = extras.getString("key_address");
            String paymentMethod = extras.getString("key_paymentmethod");
            ArrayList<String> orderNames = extras.getStringArrayList("key_ordernames");
            ArrayList<Integer> orderQty = extras.getIntegerArrayList("key_orderqty");
            double total = extras.getDouble("key_total");

            if (orderNames != null && orderQty != null) {
                ArrayList<SummaryItem> summaryList = new ArrayList<>();
                for (int i = 0; i < orderNames.size(); i++) {
                    summaryList.add(new SummaryItem(orderNames.get(i), orderQty.get(i)));
                }

                ListView lvSummaryItems = view.findViewById(R.id.lv_summaryItems);
                SummaryAdapter summaryAdapter = new SummaryAdapter(requireContext(), summaryList);
                lvSummaryItems.setAdapter(summaryAdapter);

            }

            tv_name.setText(name);
            tv_orderType.setText(orderType);
            tv_address.setText(address);
            tv_paymentMethod.setText(paymentMethod);

            Receipt = "Name: " + name + "\n"
                    + "Order Type: " + orderType + "\n"
                    + "Address: " + address + "\n"
                    + "Payment Method: " + paymentMethod + "\n\n"
                    + "Order Details:\n";

            // ✅ Populate summary list dynamically
            if (orderNames != null && orderQty != null && !orderNames.isEmpty()) {
                ArrayList<SummaryItem> summaryItems = new ArrayList<>();
                for (int i = 0; i < orderNames.size(); i++) {
                    summaryItems.add(new SummaryItem(orderNames.get(i), orderQty.get(i)));
                    Receipt += orderNames.get(i) + " x" + orderQty.get(i) + "\n";
                }

                SummaryAdapter adapter = new SummaryAdapter(requireContext(), summaryItems);
                lv_summaryItems.setAdapter(adapter);
                setListViewHeightBasedOnChildren(lv_summaryItems);
            }

            String bill = "Rs. " + total;
            tv_totalBill.setText(bill);
            Receipt += "\nTotal: " + bill;
        }

        btnShareOrder.setOnClickListener(v -> {
            Intent sendIntent = new Intent(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, Receipt);
            sendIntent.setType("text/plain");
            Intent shareIntent = Intent.createChooser(sendIntent, "Share your order via");
            startActivity(shareIntent);
        });

        btnDone.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStack());
    }

    private void init(View view) {
        tv_name = view.findViewById(R.id.tv_Name);
        tv_orderType = view.findViewById(R.id.tv_OrderType);
        tv_address = view.findViewById(R.id.tv_Address);
        tv_paymentMethod = view.findViewById(R.id.tv_PaymentMethod);
        tv_totalBill = view.findViewById(R.id.tv_TotalBill);
        btnShareOrder = view.findViewById(R.id.btnShareOrder);
        btnDone = view.findViewById(R.id.btnDone);
        lv_summaryItems = view.findViewById(R.id.lv_summaryItems);
    }
}
