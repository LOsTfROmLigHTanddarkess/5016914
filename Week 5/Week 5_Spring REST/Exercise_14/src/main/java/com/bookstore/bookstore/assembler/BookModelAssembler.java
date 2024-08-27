package com.bookstore.bookstore.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.bookstore.bookstore.controller.BookController;
import com.bookstore.bookstore.entity.Book;
import com.bookstore.bookstore.model.BookModel;

@Component
public class BookModelAssembler extends RepresentationModelAssemblerSupport<Book, BookModel> {

    public BookModelAssembler() {
        super(BookController.class, BookModel.class);
    }

    @Override
    public BookModel toModel(Book book) {
        BookModel bookModel = new BookModel(book);
        bookModel.add(linkTo(methodOn(BookController.class).getBookById(book.getId())).withSelfRel());
        bookModel.add(linkTo(methodOn(BookController.class).getAllBooks()).withRel("books"));
        return bookModel;
    }
}
