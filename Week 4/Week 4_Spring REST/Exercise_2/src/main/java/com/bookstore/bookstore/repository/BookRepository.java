package com.bookstore.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.bookstore.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    
}
