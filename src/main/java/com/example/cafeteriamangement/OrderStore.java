package com.example.cafeteriamangement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class OrderStore {

    private static final ObservableList<Order> orders = FXCollections.observableArrayList();

    public static ObservableList<Order> getOrders() {
        return orders;
    }

    public static void addOrder(Order order) {
        orders.add(order);
    }
}
