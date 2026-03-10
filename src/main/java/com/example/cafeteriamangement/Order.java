package com.example.cafeteriamangement;

public class Order {
    private int orderId;
    private String customerName;
    private double total;
    private static int orderTrack = 101;

    public  Order(String customerName, double total) {
        this.orderId = orderTrack++;
        this.customerName = customerName;
        this.total = total;
    }
    public int getOrderId() {
        return orderId;
    }

    public String getCustomerName() {return customerName;}

    public double getTotal() {return total;}

    public String getOrderDetails(){
        return "Order #" + orderId +
                "\nCustomer: " +  customerName +
                "\nTotal: " + total;
    }
}
