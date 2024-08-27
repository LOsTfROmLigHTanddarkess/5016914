package com.bookstore.bookstore.controller;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.bookstore.bookstore.entity.Book;
import com.bookstore.bookstore.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@SpringBootTest
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookRepository bookRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Book book;

    @BeforeEach
    void setUp() {
        book = new Book();
        book.setId(1L);
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setPrice(19.99);
        book.setIsbn("1234567890");
    }

    @Test
    public void contextLoads() {
        // Verify that the application context loads successfully
    }

    @Test
    void testGetAllBooks() throws Exception {
        given(bookRepository.findAll()).willReturn(Collections.singletonList(book));

        mockMvc.perform(MockMvcRequestBuilders.get("/books")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void testGetBookById() throws Exception {
        given(bookRepository.findById(1L)).willReturn(Optional.of(book));

        mockMvc.perform(MockMvcRequestBuilders.get("/books/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void testCreateBook() throws Exception {
        given(bookRepository.save(book)).willReturn(book);

        mockMvc.perform(MockMvcRequestBuilders.post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void testUpdateBook() throws Exception {
        Book updatedBook = new Book();
        updatedBook.setTitle("Updated Title");
        updatedBook.setAuthor("Updated Author");
        updatedBook.setPrice(29.99);
        updatedBook.setIsbn("0987654321");

        given(bookRepository.findById(1L)).willReturn(Optional.of(book));
        given(bookRepository.save(book)).willReturn(updatedBook);

        mockMvc.perform(MockMvcRequestBuilders.put("/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedBook)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void testDeleteBook() throws Exception {
        given(bookRepository.findById(1L)).willReturn(Optional.of(book));

        mockMvc.perform(MockMvcRequestBuilders.delete("/books/1"))
                .andExpect(status().isUnauthorized());

        //verify(bookRepository).delete(book);
    }
}

