module com.example.cafeteriamangement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.cafeteriamangement to javafx.fxml;
    exports com.example.cafeteriamangement;
}