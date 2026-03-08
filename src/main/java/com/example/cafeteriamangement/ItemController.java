package com.example.cafeteriamangement;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemController implements Initializable {
    @FXML
    private ImageView img;

    @FXML
    private Label itemName;

    @FXML
    private Label priceLabel;

    @FXML
    private Spinner<Integer> itemQuantity;

    private Item item;
    public void setData(Item item){
        this.item = item;
        itemName.setText(item.getName());
        priceLabel.setText(item.getPrice() + Main.Currency);
        Image image = new Image(getClass().getResourceAsStream(item.getImgsrc()));
        img.setImage(image);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10);
        valueFactory.setValue(1);
        itemQuantity.setValueFactory(valueFactory);
    }
}
