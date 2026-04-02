module com.example.cafeteriamangement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.web;
    requires org.json;
    requires java.net.http;


    opens com.example.cafeteriamangement to javafx.fxml;
    exports com.example.cafeteriamangement;
}