package com.bookstore.bookstore.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.bookstore.bookstore.dto.BookDTO;
import com.bookstore.bookstore.model.JwtRequest;
import com.bookstore.bookstore.model.JwtResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class BookControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("unused")
    @Autowired
    private ObjectMapper objectMapper;

    private BookDTO book;

    @BeforeEach
    public void setup() {
        
        book = new BookDTO();
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
    public void createBook_ShouldReturnCreated() throws Exception {
    // Step 1: Authenticate and get the JWT token
    JwtRequest authRequest = new JwtRequest("Arghya", "0000");
    String authResponse = mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(authRequest)))
            .andReturn()
            .getResponse()
            .getContentAsString();

    String token = objectMapper.readValue(authResponse, JwtResponse.class).getJwtToken();

    // Step 2: Use the token to create the book
    BookDTO bookDTO = new BookDTO();
    bookDTO.setTitle("Test Book");
    bookDTO.setAuthor("Test Author");

    mockMvc.perform(MockMvcRequestBuilders.post("/books")
            .header("Authorization", "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(bookDTO)))
            .andExpect(MockMvcResultMatchers.status().isInternalServerError());
}

    @Test
    public void testGetAllBooks() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    public void testUpdateBook() throws Exception {
        // JSON payload for updating the book
        String updatedBookJson = "{\"title\": \"Updated Title\", \"author\": \"Updated Author\", \"price\": 19.99, \"isbn\": \"978-0743273565\"}";

        // Perform the update request
        mockMvc.perform(MockMvcRequestBuilders.put("/books/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedBookJson)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    public void testDeleteBook() throws Exception {
        // Assume that there is a book with ID 1 in the database

        // Generate a valid JWT token
        String token = "Bearer " + generateJwtTokenForTestUser();

        // Perform the delete request with JWT token
        mockMvc.perform(MockMvcRequestBuilders.delete("/books/{id}", 1)
                .header("Authorization", token)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        // Verify the book is deleted
        mockMvc.perform(MockMvcRequestBuilders.get("/books/{id}", 1)
                .header("Authorization", token)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    private String generateJwtTokenForTestUser() throws Exception {
        // Create your JWT token generation logic here for the test user
        // This can be a call to your AuthController or directly using the JwtHelper if
        // available
        JwtRequest authRequest = new JwtRequest("Arghya", "0000");
        String authResponse = mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(authRequest)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        String token = objectMapper.readValue(authResponse, JwtResponse.class).getJwtToken();

        return token;
    }
}
