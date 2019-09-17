package com.mkolongo.bookshopsystem.repositories;

import com.mkolongo.bookshopsystem.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findBooksByReleaseDateAfter(LocalDate date);

    List<Book> findBooksByReleaseDateBefore(LocalDate date);

    List<Book> findBooksByAuthor_FirstNameAndAuthor_LastName(String firstName, String lastName);
    
    List<Book> findBooksByAgeRestriction(AgeRestriction ageRestriction);

    @Query("select b from com.mkolongo.bookshopsystem.entities.Book as b " +
            "where b.editionType = com.mkolongo.bookshopsystem.helpers.EditionType.GOLD " +
            "and b.copies < 5000")
    List<Book> findBooksByEditionTypeAndCopiesLessThan();

    @Query("select b from com.mkolongo.bookshopsystem.entities.Book as b " +
            "where b.price < 5 or b.price > 40")
    List<Book> findBooksByPriceLessThanOrPriceGreaterThan();

    @Query(value = "select * from bookshop_system.books as b where year(b.release_date) <> 2000", nativeQuery = true)
    List<Book> findBooksByReleaseDate_YearIsNot();

    List<Book> findBooksByTitleContaining(String pattern);

    @Query("select b from com.mkolongo.bookshopsystem.entities.Book as b join b.author as a " +
            "where substring(b.author.lastName, 1, :lettersLength) = :letters")
    List<Book> findBooksByAuthorLastNameStartingWith(@Param(value = "lettersLength") int length,
                                                     @Param(value = "letters") String letters);

    @Query("select count(b) from com.mkolongo.bookshopsystem.entities.Book as b where length(b.title) > ?1")
    int countBooksByTitleGreaterThan(int length);

    Book findBookByTitle(String title);

    @Modifying
    @Transactional
    @Query("update com.mkolongo.bookshopsystem.entities.Book as b set b.copies = b.copies + :bookCopies where b.releaseDate > :date")
    int updateBookCopiesReleasedAfter(@Param(value = "date") LocalDate date,
                                      @Param(value = "bookCopies") int bookCopies);

    @Modifying
    @Transactional
    @Query("delete from com.mkolongo.bookshopsystem.entities.Book as b where b.copies <= ?1")
    int deleteBooksWithCopiesLessThan(int copies);

    @Procedure(procedureName = "usp_get_total_books_written_by_author", outputParameterName = "books_count")
    int countBooksByAuthorFirstNameAndAuthorLastName(@Param(value = "first_author_name") String firstName,
                                                     @Param(value = "last_author_name") String lastName);
}
