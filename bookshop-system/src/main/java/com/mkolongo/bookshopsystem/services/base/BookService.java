package com.mkolongo.bookshopsystem.services.base;

import com.mkolongo.bookshopsystem.entities.Book;

import java.io.IOException;
import java.util.List;

public interface BookService {

    void seedBooks() throws IOException;

    List<Book> getBooksAfterYear2000();

    List<Book> getBooksBeforeYear1990();

    List<Book> getAllBooks();

    List<Book> getBooksByAuthor(String firstName, String lastName);
}
