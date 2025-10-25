package com.example.fastybites;

public class SummaryItem {
    private final String itemName;
    private final int quantity;

    public SummaryItem(String itemName, int quantity) {
        this.itemName = itemName;
        this.quantity = quantity;
    }

    public String getItemName() { return itemName; }
    public int getQuantity() { return quantity; }
}
