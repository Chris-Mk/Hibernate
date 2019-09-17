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
    
    List<Book> getBooksByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> getGoldenBooksWithLessThan5000Copies();

    List<Book> getBooksByPriceRange();

    List<Book> getBooksNotReleasedInYear();

    List<Book> getBooksWithTitleContaining(String pattern);

    List<Book> getBooksByAuthorsLastNameStartingWith(String letters);

    int getBooksTitleLongerThan(int length);

    List<String> getTotalCopiesOfAllAuthors();

    String getAllBooksByTitle(String title);

    int increaseBookCopiesReleasedAfter(LocalDate date, int copies);

    int deleteBooksWithCopiesLessThan(int copies);

    int getBooksCountWrittenBy(String firstName, String lastName);
}
