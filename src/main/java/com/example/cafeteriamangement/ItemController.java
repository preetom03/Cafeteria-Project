package com.example.cafeteriamangement;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;

public class ItemController {
    @FXML
    private ImageView img;

    @FXML
    private Label itemName;

    @FXML
    private Label priceLabel;

    private Item item;
    public void setData(Item item){
        this.item = item;
        itemName.setText(item.getName());
        priceLabel.setText(item.getPrice() + Main.Currency);
        Image image = new Image(getClass().getResourceAsStream(item.getImgsrc()));
        img.setImage(image);
    }
}
