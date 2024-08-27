package com.bookstore.bookstore.mapper;

import com.bookstore.bookstore.dto.BookDTO;
import com.bookstore.bookstore.entity.Book;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-27T17:29:18+0530",
    comments = "version: 1.5.2.Final, compiler: Eclipse JDT (IDE) 3.39.0.v20240725-1906, environment: Java 17.0.11 (Eclipse Adoptium)"
)
public class BookMapperImpl implements BookMapper {

    @Override
    public BookDTO bookToBookDTO(Book book) {
        if ( book == null ) {
            return null;
        }

        BookDTO bookDTO = new BookDTO();

        bookDTO.setAuthor( book.getAuthor() );
        bookDTO.setId( book.getId() );
        bookDTO.setIsbn( book.getIsbn() );
        bookDTO.setPrice( book.getPrice() );
        bookDTO.setTitle( book.getTitle() );

        return bookDTO;
    }

    @Override
    public Book bookDTOToBook(BookDTO bookDTO) {
        if ( bookDTO == null ) {
            return null;
        }

        Book book = new Book();

        book.setAuthor( bookDTO.getAuthor() );
        book.setId( bookDTO.getId() );
        book.setIsbn( bookDTO.getIsbn() );
        book.setPrice( bookDTO.getPrice() );
        book.setTitle( bookDTO.getTitle() );

        return book;
    }
}
