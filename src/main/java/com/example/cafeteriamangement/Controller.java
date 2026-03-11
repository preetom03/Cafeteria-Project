package com.example.cafeteriamangement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Controller implements Initializable {
    @FXML
    private Label changeAmount;

    @FXML
    private Label totalAmount;

    @FXML
    private TextField payAmount;

    @FXML
    private FlowPane flowpane;

    @FXML
    private Button menuSwitchBtn;

    @FXML
    private ScrollPane scroll;
    private boolean breakfastMode = true;

    @FXML
    private void handleMenuSwitch(){
        if(breakfastMode){
            menuSwitchBtn.setText("Lunch Menu");
        }
        else{
            menuSwitchBtn.setText("Breakfast Menu");
        }
        breakfastMode = !breakfastMode;
    }

    @FXML
    private Button payButton;

    @FXML
    private Button confirmButton;

    @FXML
    private TableView<OrderItem> orderTable;

    @FXML
    private TableColumn<OrderItem, String> itemColumn;

    @FXML
    private TableColumn<OrderItem, Integer> quantityColumn;

    @FXML
    private TableColumn<OrderItem, Double> priceColumn;

    private List<Item> items = new ArrayList<>();

    private List<Item> getData(){
        List<Item> items = new ArrayList<>();
        Item item;
        for(int i = 0; i<20; i++){
            item = new Item();
            item.setName("Sandwich");
            item.setPrice(30);
            item.setImgsrc("/images/test.png");
            items.add(item);
        }

        return items;
    }

    private ObservableList<OrderItem> orderList =
            FXCollections.observableArrayList();

    double totalPayment = 0; //The Total amount that need to be paid for orders

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        itemColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        orderTable.setItems(orderList);

        payButton.setDisable(true);
        confirmButton.setDisable(false);

        items.addAll(getData());
        int column = 0;
        int row = 0;
        try {
            for(Item item: items) {
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("itemCard.fxml"));

                AnchorPane anchorPane = fxmlloader.load();
                ItemController itemController = fxmlloader.getController();
                itemController.setData(item);
                itemController.setMainController(this);

                flowpane.getChildren().add(anchorPane);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void addItemToTable(Item item, int quantity){ //Adds item information to table
        double total = item.getPrice() * quantity;

        OrderItem order =
                new OrderItem(item.getName(), quantity, total);

        totalPayment += total;

        totalAmount.setText(String.valueOf(totalPayment) + Main.Currency);

        orderList.add(order);
    }

    private Order currentOrder; // for tracking order details

    @FXML
    private void handleConfirm(){ // for Confirm button

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Customer Info");
        dialog.setHeaderText("Enter Customer Name");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(name ->{
            double total = calculateTotal();
            Order order = new Order(name, total);
            currentOrder = order;

            payButton.setDisable(false);
            confirmButton.setDisable(true);

            // For testing only*
            System.out.println("Order created");
            System.out.println("Order ID: " + order.getOrderId());
            System.out.println("Name    : " + name);
            System.out.println("Total   : " + total);
        });
    }

    private double calculateTotal(){
        double total = 0;
        for(OrderItem item: orderList){
            total += item.getTotalPrice();
        }
        return total;
    }

    private void orderReceipt(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Order Receipt");
        alert.setHeaderText("Order # " + currentOrder.getOrderId());

        alert.setContentText(currentOrder.getOrderDetails());
        alert.showAndWait();
    }

    @FXML
    private void handlePay(){  // for Pay button
        if(payAmount.getText().isEmpty()){
            showError("Please enter a payment amount");
            return;
        }

        double totalPaid;
        try {
            totalPaid = Double.parseDouble(payAmount.getText());
        }
        catch (NumberFormatException e){
            showError("Please enter a valid payment amount.");
            return;
        }
        double change = totalPaid - totalPayment;
        if(change < 0){
            showError("Insufficient amount!\nPlease enter a  valid amount.");
            return;
        }
        changeAmount.setText(String.valueOf(change) +  Main.Currency);
        orderReceipt();
        resetOrder(); // clearing order table
    }

    private void resetOrder(){ // resetting order after each payment
        orderList.clear();
        totalAmount.setText("");
        totalPayment = 0;

        payAmount.clear();
        changeAmount.setText("");
        currentOrder = null;

        payButton.setDisable(true);
        confirmButton.setDisable(false);
    }

    private void showError(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Payment Error!");
        alert.setHeaderText("Payment failed!");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
