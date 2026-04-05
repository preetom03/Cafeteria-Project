package com.example.cafeteriamangement;

import java.util.List;

public class Order {
    private int orderId;
    private String customerName;
    private double total;
    private List<OrderItem> items;
    private static int orderTrack = 101;

    public  Order(String customerName, double total, List<OrderItem> items) {
        this.orderId = orderTrack++;
        this.customerName = customerName;
        this.total = total;
        this.items = items;
    }
    public int getOrderId() {
        return orderId;
    }

    public String getCustomerName() {return customerName;}

    public double getTotal() {return total;}

    public String getItemsAsString() {
        StringBuilder sb = new StringBuilder();

        for (OrderItem item : items) {
            sb.append(item.getName())
                    .append(" x")
                    .append(item.getQuantity())
                    .append("\n");
        }
        return sb.toString();
    }

    public String getOrderDetails(){
        return "Order #" + orderId +
                "\nCustomer: " +  customerName +
                "\nItems: " + getItemsAsString() +
                "\nTotal: " + total;
    }
}
