package com.example.fastybites;

public class FoodItem {
    private String name;
    private final double price;
    private final int imageResId;
    private int quantity;
    private boolean selected;

    public FoodItem(String name, double price, int imageResId) {
        this.name = name;
        this.price = price;
        this.imageResId = imageResId;
        this.quantity = 0;
        this.selected = false;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getImageResId() { return imageResId; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public boolean isSelected() { return selected; }
    public void setSelected(boolean selected) { this.selected = selected; }
}
