package com.bookstore.bookstore;

import java.awt.Desktop;
import java.net.URI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookstoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
        openSwaggerUI();
    }

    private static void openSwaggerUI() {
        try {
            String swaggerUrl = "http://localhost:8080/swagger-ui.html";
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI(swaggerUrl));
            } else {
                System.out.println("Please open the following URL in your browser:");
                System.out.println(swaggerUrl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}