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
}
