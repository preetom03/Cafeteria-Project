package com.example.cafeteriamangement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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

                flowpane.getChildren().add(anchorPane);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
