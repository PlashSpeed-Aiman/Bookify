package com.example.bookify.utilities;

import com.example.bookify.models.Book;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public  class BookRetriever {
    public static ArrayList<Book> getBooksFromFile(File file) {
        try(Scanner scanner = new Scanner(file)) {
            ArrayList<Book> books = new ArrayList<>();
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                String title = parts[0];
                String author = parts[1];
                double price = Double.parseDouble(parts[2]);
                String imageDirectory = parts[3];
                Book book = new Book(title, author, price);
                books.add(book);
            }
            return books;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
