module com.example.bookify {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.bookify to javafx.fxml;
    exports com.example.bookify;
    exports com.example.bookify.models;
    exports com.example.bookify.pages;
    exports com.example.bookify.utilities;
}