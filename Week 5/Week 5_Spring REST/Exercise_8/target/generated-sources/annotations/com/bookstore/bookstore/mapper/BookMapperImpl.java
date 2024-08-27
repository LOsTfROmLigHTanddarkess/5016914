package com.bookstore.bookstore.mapper;

import com.bookstore.bookstore.dto.BookDTO;
import com.bookstore.bookstore.entity.Book;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-25T14:27:44+0530",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 21.0.4 (Eclipse Adoptium)"
)
public class BookMapperImpl implements BookMapper {

    @Override
    public BookDTO bookToBookDTO(Book book) {
        if ( book == null ) {
            return null;
        }

        BookDTO bookDTO = new BookDTO();

        return bookDTO;
    }

    @Override
    public Book bookDTOToBook(BookDTO bookDTO) {
        if ( bookDTO == null ) {
            return null;
        }

        Book book = new Book();

        return book;
    }
}
