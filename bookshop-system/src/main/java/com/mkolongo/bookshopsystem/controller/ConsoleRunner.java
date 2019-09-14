package com.mkolongo.bookshopsystem.controller;

import com.mkolongo.bookshopsystem.entities.Book;
import com.mkolongo.bookshopsystem.services.base.AuthorService;
import com.mkolongo.bookshopsystem.services.base.BookService;
import com.mkolongo.bookshopsystem.services.base.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.stream.Collectors;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final BookService bookService;

    public ConsoleRunner(AuthorService authorService, CategoryService categoryService, BookService bookService) {
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws IOException {
        authorService.seedAuthors();
        categoryService.seedCategories();
        bookService.seedBooks();

        bookService.getBooksAfterYear2000().forEach(book -> System.out.println(book.getTitle()));

        bookService.getBooksBeforeYear1990()
                .forEach(book -> System.out.println(book.getAuthor().getFirstName() + " " + book.getAuthor().getLastName()));

        bookService.getAllBooks()
                .stream()
                .collect(Collectors.groupingBy(Book::getAuthor))
                .entrySet()
                .stream()
                .sorted((a1, a2) -> Integer.compare(a2.getValue().size(), a1.getValue().size()))
                .forEach(entry -> System.out.println(String.format("%s %s -> %d books",
                        entry.getKey().getFirstName(),
                        entry.getKey().getLastName(),
                        entry.getValue().size())));

        bookService.getBooksByAuthor("George", "Powell")
                .stream()
                .sorted((b1, b2) -> {
                    int sort = b2.getReleaseDate().compareTo(b1.getReleaseDate());

                    if (sort == 0) {
                        sort = b1.getTitle().compareTo(b2.getTitle());
                    }
                    return sort;
                })
                .forEach(book -> System.out.println(String.format("%s || %s || %d copies",
                        book.getTitle(),
                        book.getReleaseDate().toString(),
                        book.getCopies())));
    }
}
