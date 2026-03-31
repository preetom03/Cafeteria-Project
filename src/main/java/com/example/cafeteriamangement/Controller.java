package com.example.cafeteriamangement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

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
    private Button payButton;

    @FXML
    private Button confirmButton;

    @FXML
    private Button clearButton;

    @FXML
    private TableView<OrderItem> orderTable;

    @FXML
    private TableColumn<OrderItem, String> itemColumn;

    @FXML
    private TableColumn<OrderItem, Integer> quantityColumn;

    @FXML
    private TableColumn<OrderItem, Double> priceColumn;

    private List<Item> items = new ArrayList<>();

    private List<Item> getBreakfastData(){
        return List.of(
                new Item("Khichuri - 1 plate", 20, "/images/khichuri.jpg"),
                new Item("Khichuri - 1/2 plate", 15, "/images/khichuri.jpg"),
                new Item("Luchi", 7, "/images/luchi.jpeg"),
                new Item("Alu bhorta", 7, "/images/alu_bhorta.jpg"),
                new Item("Dim bhaji", 20, "/images/dim_bhaji.jpeg"),
                new Item("Shobji", 10, "/images/shobji.jpg"),
                new Item("Buter daal", 10, "/images/buter_daal.jpg"),
                new Item("Shingara", 6, "/images/shingara.jpg"),
                new Item("Dim chop", 10, "/images/dim_chop.jpg"),
                new Item("Alur chop", 7,  "/images/alur_chop.jpg")
        );
    }
    private List<Item> getLunchData(){
        return List.of(
            new Item("Shada bhat - 1 plate", 15, "/images/shada_bhat.jpg"),
            new Item("Shada bhat - 1/2 plate", 10, "/images/shada_bhat.jpg"),
            new Item("Polao - 1 plate", 30, "/images/polao.jpg"),
            new Item("Polao - 1/2 plate", 20, "/images/polao.jpg"),
            new Item("Chicken roast", 50, "/images/chicken_roast.jpg"),
            new Item("Broiler chicken", 35, "/images/broiler_chicken.jpg"),
            new Item("Boiled egg", 25, "/images/boiled_egg.jpg"),
            new Item("Shobji", 10, "/images/shobji.jpg"),
            new Item("Patla daal", 10, "/images/patla_daal.jpg"),
            new Item("Beef - 5 pieces", 100, "/images/gorur_mangsho.jpg"),
            new Item("Beef - 3 pieces", 70, "/images/gorur_mangsho.jpg")
        );
    }

    private List<Item> getFastfoodData(){
        return List.of(
                new Item("Burger (Egg)", 35, "/images/egg_burger.jpg"),
                new Item("Burger (Kima)", 40, "/images/kima_burger.jpg"),
                new Item("Pizza", 70, "/images/pizza.jpg"),
                new Item("Shobji Roll", 25, "/images/shobji_roll.jpg"),
                new Item("Sandwich (Vegetable)", 25, "/images/sandwich.jpg"),
                new Item("Sandwich (Egg)", 30, "/images/sandwich.jpg")
        );
    }

    private List<Item> getBeverageData(){
        return List.of(
                new Item("Red Tea", 7, "/images/red_tea.jpg"),
                new Item("Milk Tea", 10, "/images/milk_tea.jpg"),
                new Item("Coffee", 20, "/images/coffee.jpg")
        );
    }

    private void loadItem(List<Item>newItems){
        items.clear();
        items.addAll(newItems);

        flowpane.getChildren().clear();

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
    public void switchToBreakfast(ActionEvent actionEvent) throws IOException {
        loadItem(getBreakfastData());
    }
    public void switchToLunch(ActionEvent event) throws IOException {
        loadItem(getLunchData());
    }

    public void switchToFastfood(ActionEvent actionEvent) throws IOException {
        loadItem(getFastfoodData());
    }

    public void switchToBeverages(ActionEvent actionEvent) throws IOException {
        loadItem(getBeverageData());
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
        int column = 0;
        int row = 0;

        loadItem(getBreakfastData());
    }

    @FXML
    public void switchAdmin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("admin.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
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

        if(totalPayment == 0){
            confirmError("Please add item to the menu first");
            return;
        }

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Customer Info");
        dialog.setHeaderText("Enter Customer Name");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(name ->{
            if(name.isEmpty()){
                confirmError("Customer Name cannot be empty!");
                return;
            }
            double total = calculateTotal();
            Order order = new Order(name, total);
            currentOrder = order;

            payButton.setDisable(false);
            confirmButton.setDisable(true);

            /*
            // For testing only*
            System.out.println("Order created");
            System.out.println("Order ID: " + order.getOrderId());
            System.out.println("Name    : " + name);
            System.out.println("Total   : " + total);
            */
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
        changeAmount.setText(change +  Main.Currency);

        OrderStore.addOrder(currentOrder); // storing the order after pay

        orderReceipt();
        resetOrder(); // clearing order table
    }

    @FXML
    private void handleClear(){ // clearing order
        resetOrder();
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
    private void confirmError(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Confirm Error!");
        alert.setHeaderText("Confirm failed!");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
