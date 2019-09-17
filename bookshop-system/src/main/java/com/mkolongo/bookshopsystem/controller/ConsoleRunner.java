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
        
        --------books queries----------
//        booksTitlesByAgeRestriction();
//        goldenBooks();
//        booksByPrice();
//        notReleasedBooks();
//        booksReleasedBeforeDate();
//        booksSearch();
//        bookTitlesSearch();
//        countBooks();
//        totalBookCopies();
//        reducedBook();
//        increaseBookCopies();
//        removeBooks();

//        ------------authors queries--------------
//        authorsSearch();

        String firstName = this.scanner.nextLine();
        String lastName = this.scanner.nextLine();

        final int booksCount = this.bookService.getBooksCountWrittenBy(firstName, lastName);

        System.out.printf("%s %s has written %d books%n", firstName, lastName, booksCount);
    }
    
    private void removeBooks() {
        int bookCopies = Integer.parseInt(scanner.nextLine());

        System.out.println(this.bookService.deleteBooksWithCopiesLessThan(bookCopies));
    }

    private void increaseBookCopies() {
        LocalDate releaseDate = LocalDate.parse(this.scanner.nextLine(), DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
        int bookCopies = Integer.parseInt(scanner.nextLine());

        final int updatedBooks = this.bookService.increaseBookCopiesReleasedAfter(releaseDate, bookCopies);
        int totalBooksAdded = updatedBooks * bookCopies;

        System.out.println(totalBooksAdded);
        System.out.printf("%d books are released after %s, so total of %d book copies were added%n",
                updatedBooks,
                releaseDate,
                totalBooksAdded);
    }

    private void reducedBook() {
        String bookTitle = this.scanner.nextLine();

        final String book = this.bookService.getAllBooksByTitle(bookTitle);
        System.out.println(book);
    }

    private void totalBookCopies() {
        this.bookService.getTotalCopiesOfAllAuthors()
                .forEach(System.out::println);
    }

    private void countBooks() {
        int bookLength = Integer.parseInt(scanner.nextLine());

        final int booksCount = this.bookService.getBooksTitleLongerThan(bookLength);

        System.out.println(booksCount);
        System.out.printf("There are %d books with longer title than %d symbols%n", booksCount, bookLength);
    }

    private void bookTitlesSearch() {
        String pattern = this.scanner.nextLine();

        this.bookService.getBooksByAuthorsLastNameStartingWith(pattern)
                .forEach(book -> System.out.println(String.format("%s (%s)",
                        book.getTitle(),
                        String.join(" ", book.getAuthor().getFirstName(), book.getAuthor().getLastName()))));
    }

    private void booksSearch() {
        String pattern  = this.scanner.nextLine();

        this.bookService.getBooksWithTitleContaining(pattern)
                .forEach(book -> System.out.println(book.getTitle()));
    }

    private void authorsSearch() {
        String letters = this.scanner.nextLine();

        this.authorService.getAuthorsByPattern(letters)
                .forEach(author -> System.out.println(author.getFirstName() + " " + author.getLastName()));
    }

    private void booksReleasedBeforeDate() {
        LocalDate releasedDate = LocalDate.parse(this.scanner.nextLine(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        this.bookService.getBooksBeforeYear(releasedDate)
                .forEach(book -> System.out.println(String.format("%s %s %s",
                        book.getTitle(),
                        book.getEditionType(),
                        book.getPrice())));
    }

    private void notReleasedBooks() {
        this.bookService.getBooksNotReleasedInYear()
                .forEach(book -> System.out.println(book.getTitle()));
    }

    private void booksByPrice() {
        this.bookService.getBooksByPriceRange()
                .forEach(book -> System.out.println(book.getTitle()  + " - $" + book.getPrice()));
    }

    private void goldenBooks() {
        this.bookService.getGoldenBooksWithLessThan5000Copies()
                .forEach(book -> System.out.println(book.getTitle()));
    }

    private void booksTitlesByAgeRestriction() {
        String ageRestriction = this.scanner.nextLine().toUpperCase();

        this.bookService.getBooksByAgeRestriction(AgeRestriction.valueOf(ageRestriction))
                .forEach(book -> System.out.println(book.getTitle()));
    }
}
