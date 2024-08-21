package com.bookstore.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.bookstore.entity.Book;
import com.bookstore.bookstore.exception.BookNotFoundException;
import com.bookstore.bookstore.repository.BookRepository;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    // GET all books
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "Value");
        return new ResponseEntity<>(books, headers, HttpStatus.OK);
    }

    // GET a book by ID
    // @GetMapping("/{id}")
    // public ResponseEntity<Book> getBookById(@PathVariable Long id) {
    //     return bookRepository.findById(id)
    //             .map(book -> {
    //                 HttpHeaders headers = new HttpHeaders();
    //                 headers.add("Custom-Header", "Book-Found");
    //                 return new ResponseEntity<>(book, headers, HttpStatus.OK);
    //             })
    //             .orElseGet(() -> {
    //                 HttpHeaders headers = new HttpHeaders();
    //                 headers.add("Custom-Header", "Book-Not-Found");
    //                 return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    //             });
    // }

    // POST a new book
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book createdBook = bookRepository.save(book);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/books/" + createdBook.getId());
        return new ResponseEntity<>(createdBook, headers, HttpStatus.CREATED);
    }

    // PUT to update an existing book
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        return bookRepository.findById(id)
                .map(book -> {
                    book.setTitle(bookDetails.getTitle());
                    book.setAuthor(bookDetails.getAuthor());
                    book.setPrice(bookDetails.getPrice());
                    book.setIsbn(bookDetails.getIsbn());
                    HttpHeaders headers = new HttpHeaders();
                    headers.add("Custom-Header", "Book-Updated");
                    return new ResponseEntity<>(bookRepository.save(book), headers, HttpStatus.OK);
                })
                .orElseGet(() -> {
                    HttpHeaders headers = new HttpHeaders();
                    headers.add("Custom-Header", "Book-Not-Found");
                    return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
                });
    }

    // DELETE a book by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable Long id) {
        return bookRepository.findById(id)
                .map(book -> {
                    bookRepository.delete(book);
                    HttpHeaders headers = new HttpHeaders();
                    headers.add("Custom-Header", "Book-Deleted");
                    return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
                })
                .orElseGet(() -> {
                    HttpHeaders headers = new HttpHeaders();
                    headers.add("Custom-Header", "Book-Not-Found");
                    return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
                });
    }
    //Exception method
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookBYId(@PathVariable Long id) {
        return bookRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));
    }

}
