package com.bookstore.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.bookstore.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Additional query methods (if needed) can be defined here
}
