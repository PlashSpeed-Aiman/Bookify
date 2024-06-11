package com.example.bookify;

import com.example.bookify.pages.LoginPage;
import com.example.bookify.pages.MainPage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        stage.setTitle("Hello!");
        stage.setScene(new MainPage().getScene());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}