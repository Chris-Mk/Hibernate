package com.mkolongo.bookshopsystem.services;

import com.mkolongo.bookshopsystem.entities.Author;
import com.mkolongo.bookshopsystem.entities.Book;
import com.mkolongo.bookshopsystem.entities.Category;
import com.mkolongo.bookshopsystem.helpers.AgeRestriction;
import com.mkolongo.bookshopsystem.helpers.EditionType;
import com.mkolongo.bookshopsystem.repositories.AuthorRepository;
import com.mkolongo.bookshopsystem.repositories.BookRepository;
import com.mkolongo.bookshopsystem.repositories.CategoryRepository;
import com.mkolongo.bookshopsystem.services.base.BookService;
import com.mkolongo.bookshopsystem.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
@Transactional
public class BookServiceImpl implements BookService {
    private static final String FILE_PATH = System.getProperty("user.dir")
            + "\\src\\main\\resources\\files\\books.txt";

    private static Random random = new Random();

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final FileUtil fileUtil;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository,
                           CategoryRepository categoryRepository, FileUtil fileUtil) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
        this.fileUtil = fileUtil;
    }


    @Override
    public void seedBooks() throws IOException {
        if (bookRepository.count() != 0) return;

        final String[] booksContent = fileUtil.getFileContent(FILE_PATH);

        for (String bookInfo : booksContent) {
            String[] data = bookInfo.split("\\s+");

            Book book = new Book();

            EditionType editionType = EditionType.values()[Integer.parseInt(data[0])];
            book.setEditionType(editionType);

            LocalDate localDate = LocalDate.parse(data[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
            book.setReleaseDate(localDate);

            book.setCopies(Integer.parseInt(data[2]));

            book.setPrice(new BigDecimal(data[3]));

            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(data[4])];
            book.setAgeRestriction(ageRestriction);

            StringBuilder builder = new StringBuilder();

            for (int i = 5; i < data.length; i++) {
                builder.append(data[i]).append(" ");
            }
            
            book.setTitle(builder.toString().trim());
            
            book.setAuthor(getRandomAuthor());

            Set<Category> categories = new LinkedHashSet<>();
            for (int i = 0; i < 5; i++) {
                categories.add(getRandomCategory());
            }

            book.setCategories(categories);

            bookRepository.saveAndFlush(book);
        }
    }

    @Override
    public List<Book> getBooksAfterYear2000() {
        return this.bookRepository.findBooksByReleaseDateAfter(LocalDate.parse("2000-12-31"));
    }

    @Override
    public List<Book> getBooksBeforeYear1990() {
        return this.bookRepository.findBooksByReleaseDateBefore(LocalDate.parse("1990-01-01"));
    }

    @Override
    public List<Book> getAllBooks() {
        return this.bookRepository.findAll();
    }

    @Override
    public List<Book> getBooksByAuthor(String firstName, String lastName) {
        return this.bookRepository.findBooksByAuthor_FirstNameAndAuthor_LastName(firstName, lastName);
    }
    
    @Override
    public List<Book> getBooksByAgeRestriction(AgeRestriction ageRestriction) {
        return this.bookRepository.findBooksByAgeRestriction(ageRestriction);
    }

    @Override
    public List<Book> getGoldenBooksWithLessThan5000Copies() {
        return this.bookRepository.findBooksByEditionTypeAndCopiesLessThan();
    }

    @Override
    public List<Book> getBooksByPriceRange() {
        return this.bookRepository.findBooksByPriceLessThanOrPriceGreaterThan();
    }

    @Override
    public List<Book> getBooksNotReleasedInYear() {
        return this.bookRepository.findBooksByReleaseDate_YearIsNot();
    }

    @Override
    public List<Book> getBooksWithTitleContaining(String pattern) {
        return this.bookRepository.findBooksByTitleContaining(pattern);
    }

    @Override
    public List<Book> getBooksByAuthorsLastNameStartingWith(String letters) {
        return this.bookRepository.findBooksByAuthorLastNameStartingWith(letters.length(), letters);
    }

    @Override
    public int getBooksTitleLongerThan(int length) {
        return this.bookRepository.countBooksByTitleGreaterThan(length);
    }

    @Override
    public List<String> getTotalCopiesOfAllAuthors() {
        List<String> authorsWithCopies = new ArrayList<>();

        this.bookRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(Book::getAuthor))
                .entrySet()
                .stream()
                .sorted((a1, a2) -> Integer.compare(a2.getValue().stream().mapToInt(Book::getCopies).sum(),
                        a1.getValue().stream().mapToInt(Book::getCopies).sum()))
                .forEach(a -> authorsWithCopies.add(String.format("%s - %d",
                        String.join(" ", a.getKey().getFirstName(), a.getKey().getLastName()),
                        a.getValue().stream().mapToInt(Book::getCopies).sum())));

        return authorsWithCopies;
    }

    @Override
    public String getAllBooksByTitle(String title) {
        final Book book = this.bookRepository.findBookByTitle(title);

        return String.format("%s %s %s %s",
                book.getTitle(),
                book.getEditionType(),
                book.getAgeRestriction(),
                book.getPrice());
    }

    @Override
    public int increaseBookCopiesReleasedAfter(LocalDate date, int copies) {
        return this.bookRepository.updateBookCopiesReleasedAfter(date, copies);
    }

    @Override
    public int deleteBooksWithCopiesLessThan(int copies) {
        return this.bookRepository.deleteBooksWithCopiesLessThan(copies);
    }

    @Override
    public int getBooksCountWrittenBy(String firstName, String lastName) {
        return this.bookRepository.countBooksByAuthorFirstNameAndAuthorLastName(firstName, lastName);
    }

    private Author getRandomAuthor() {
        final int randomId = random.nextInt((int) authorRepository.count() - 1) + 1;

        return this.authorRepository.getOne(randomId);
    }

    private Category getRandomCategory() {
        final int randomId = random.nextInt((int) categoryRepository.count() - 1) + 1;

        return this.categoryRepository.getOne(randomId);
    }
}
