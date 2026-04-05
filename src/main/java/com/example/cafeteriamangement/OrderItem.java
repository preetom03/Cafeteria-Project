package com.example.cafeteriamangement;

public class OrderItem {

    private String name;
    private int quantity;
    private double totalPrice;

    public OrderItem(String name, int quantity, double totalPrice) {
        this.name = name;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

}
