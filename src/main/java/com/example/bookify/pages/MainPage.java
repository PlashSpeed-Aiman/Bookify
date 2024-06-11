package com.example.bookify.pages;

import com.example.bookify.models.Book;
import com.example.bookify.utilities.BookRetriever;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

public class MainPage extends Page{
    TableView<Book> bookTable;

    private ArrayList<Book> books;
    private ObservableList<Book> searchResults;

    public MainPage() {
        bookTable = new TableView<>();
        try {
            searchResults = FXCollections.observableArrayList();
            File file = new File("./books.txt");
            books = BookRetriever.getBooksFromFile(file);
        } catch (Exception e) {
            e.printStackTrace();
            books = new ArrayList<>();
        }

    }
    @Override
    public Scene getScene() {
        VBox gridPane = new VBox();
        Label label = new Label("Welcome to Bookify!");
        label.getStyleClass().add("text-lg");

        //TableView
        // Create the TableView

        // Create the columns
        TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Book, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));

        TableColumn<Book, Number> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Add the columns to the TableView
        bookTable.getColumns().add(titleColumn);
        bookTable.getColumns().add(authorColumn);
        bookTable.getColumns().add(priceColumn);

        // Create some books and add them to the TableView
        for (Book book : books) {
            bookTable.getItems().add(book);
        }
        // Add a click event to the rows of the TableView
        bookTable.setRowFactory(tv -> {
            TableRow<Book> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton()== MouseButton.PRIMARY
                        && event.getClickCount() == 2) {

                    Book clickedRow = row.getItem();

                    // Create a new Stage for the new window
                    Stage stage = new Stage();

                    // Create a new Label with the details of the clicked book
                    VBox vbox = new VBox();
                    VBox pane = new VBox();
                    vbox.getStyleClass().add("card");
                    vbox.getChildren().addAll(
                            new Label("Title: " + clickedRow.getTitle()),
                            new Label("Author: " + clickedRow.getAuthor()),
                            new Label("Price: " + clickedRow.getPrice())
                    );

                    VBox.setMargin(vbox, new javafx.geometry.Insets(10, 10, 10, 10));

                    // Create a new Scene with the Label and set it on the Stage
                    pane.setPrefWidth(300);
                    pane.setPrefHeight(300);
                    pane.setMinWidth(300);
                    pane.setMinHeight(300);
                    vbox.setPrefSize(pane.getMinWidth(), pane.getMinHeight());
                    pane.getChildren().add(vbox);

                    Scene scene = new Scene(pane, pane.getWidth(), pane.getHeight());
                    scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
                    stage.setScene(scene);
                    stage.show();
                }
            });
            return row ;
        });
        // Add the TableView to the GridPane



        //add a search bar
        var searchBar = createSearchBar();

        gridPane.getChildren().addAll(label,searchBar, bookTable);

        // Set the horizontal and vertical alignment of the label and search bar to CENTER
        VBox.setMargin(label, new javafx.geometry.Insets(5, 5, 5, 5));
        VBox.setMargin(searchBar, new javafx.geometry.Insets(5, 5, 5, 5));
        VBox.setMargin(bookTable, new javafx.geometry.Insets(5, 5, 5, 5));


        Scene scene = new Scene(gridPane, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        return scene;
    }

    public Node createSearchBar() {
        HBox hBox = new HBox();
        TextField searchField = new TextField();
        searchField.setPromptText("Search for books");
        searchField.getStyleClass().add("text-field");
        //search field on change
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                // If the search field is empty, show all books
                bookTable.setItems(FXCollections.observableArrayList(books));
            } else {
                // Otherwise, perform the search
                searchResults.clear();
                for (Book book : books) {
                    if (book.getTitle().toLowerCase().contains(newValue.toLowerCase())) {
                        searchResults.add(book);
                    }
                }
                bookTable.setItems(searchResults);
            }
        });

        Button searchButton = new Button("Search");
        searchButton.getStyleClass().add("button-blue");
        hBox.setSpacing(5);
        hBox.getChildren().addAll(searchField, searchButton);
        return hBox;
    }
}
