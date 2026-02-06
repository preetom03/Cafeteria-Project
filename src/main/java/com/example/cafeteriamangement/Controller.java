package com.example.cafeteriamangement;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Controller {

    @FXML
    private Button menuSwitchBtn;
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
}
