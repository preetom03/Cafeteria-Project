module com.example.cafeteriamangement {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.cafeteriamangement to javafx.fxml;
    exports com.example.cafeteriamangement;
}