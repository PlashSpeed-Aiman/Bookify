package com.example.bookify.pages;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class LoginPage extends Page{

    private Pane accountRoot;
    private Pane[] accountPanes;
    public LoginPage() {

        createAccountPanes();
        createLoginSection();
        createRegisterSection();
    }

    private void createAccountPanes() {
        accountPanes = new GridPane[2];
        accountPanes[0] = new GridPane();
        accountPanes[1] = new GridPane();

    }

    private void createLoginSection() {
        VBox root = new VBox();
        root.setSpacing(5);

        Label label = new Label("Login Page");
        label.getStyleClass().add("text-lg");

        TextField username = new TextField();
        //placeholder
        username.setPromptText("Username");

        TextField password = new TextField();
        //placeholder
        password.setPromptText("Password");

        Button button = new Button("Login");
        button.getStyleClass().add("button-blue");

        username.getStyleClass().add("text-field");
        password.getStyleClass().add("text-field");

        root.getChildren().addAll(label, username, password, button);
        GridPane.setMargin(accountPanes[0], new javafx.geometry.Insets(5, 5, 5, 5));
        accountPanes[0].getChildren().addAll(root);
    }
    private void createRegisterSection() {
        VBox root = new VBox();
        root.setSpacing(5);
        Label label = new Label("Register Page");
        label.getStyleClass().add("text-lg");
        TextField username = new TextField();
        //placeholder
        username.setPromptText("Username");
        TextField password = new TextField();
        //placeholder
        password.setPromptText("Password");
        username.getStyleClass().add("text-field");
        password.getStyleClass().add("text-field");
        Button button = new Button("Register");
        button.getStyleClass().add("button-blue");
        root.getChildren().addAll(label,  username, password,button);

        accountPanes[1].getChildren().addAll(root);
        GridPane.setMargin(accountPanes[1], new javafx.geometry.Insets(5, 5, 5, 5));
        GridPane.setValignment(accountPanes[1], javafx.geometry.VPos.CENTER);
    }

    public Scene getScene() {
        Pane accountPane = new GridPane();
        VBox root = new VBox();
        Label label = new Label("Login Page");
        label.getStyleClass().add("text-lg");
        VBox accountButtons = new VBox();
        HBox buttons = new HBox();
        Button login = new Button("Login");
        login.getStyleClass().add("button-blue");
        Button register = new Button("Register");
        register.getStyleClass().add("button-blue");
        buttons.getChildren().addAll(login, register);
        buttons.getStyleClass().add("hbox");
        buttons.setAlignment(Pos.CENTER);
        VBox.setMargin(label, new javafx.geometry.Insets(5, 5, 5, 5));
        accountButtons.getChildren().addAll(label,buttons);
accountButtons.setAlignment(Pos.CENTER);
        login.setOnAction(e -> {
            accountPane.getChildren().clear();
            accountPane.getChildren().add(accountPanes[0]);
        });
        register.setOnAction(e -> {
            accountPane.getChildren().clear();
            accountPane.getChildren().add(accountPanes[1]);
        });


        root.getChildren().addAll(accountButtons, accountPane);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 320, 240);
        scene.getStylesheets().add(String.valueOf(getClass().getResource("/styles.css")));
        return scene;
    }
}
