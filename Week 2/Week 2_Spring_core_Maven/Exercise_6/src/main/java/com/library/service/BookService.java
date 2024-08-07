package com.library.service;

import org.springframework.stereotype.Service;
import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class BookService {
    private BookRepository bookRepository;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // A sample method to demonstrate functionality
    public void performService() {
        System.out.println("BookService is performing service.");
        bookRepository.performRepositoryTask();
    }
}
