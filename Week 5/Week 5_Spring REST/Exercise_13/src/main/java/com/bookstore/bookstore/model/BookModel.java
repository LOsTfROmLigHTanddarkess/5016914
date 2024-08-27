package com.bookstore.bookstore.model;

import org.springframework.hateoas.RepresentationModel;

import com.bookstore.bookstore.entity.Book;

public class BookModel extends RepresentationModel<BookModel> {
    @SuppressWarnings("unused")
    private Long id;
    @SuppressWarnings("unused")
    private String title;
    @SuppressWarnings("unused")
    private String author;
    @SuppressWarnings("unused")
    private Double price;
    @SuppressWarnings("unused")
    private String isbn;

    // Constructors, Getters, and Setters

    public BookModel(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.price = book.getPrice();
        this.isbn = book.getIsbn();
    }

    // Getters and Setters
}
