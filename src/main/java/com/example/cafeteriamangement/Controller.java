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
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.ResourceBundle;

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

    @FXML
    private void confirmPayment(){ // for confirm button of payment
        double totalPaid = Double.parseDouble(payAmount.getText());
        double change = totalPaid - totalPayment;
        changeAmount.setText(String.valueOf(change) +  Main.Currency);
    }
}
