create
    definer = root@localhost procedure usp_get_total_books_written_by_author(IN first_author_name varchar(255),
                                                                             IN last_author_name varchar(255),
                                                                             OUT books_count int)
BEGIN
    SET books_count := (SELECT count(b.id)
                        FROM books b
                                 JOIN authors a ON b.author_id = a.id
                        WHERE a.first_name = first_author_name
                          AND a.last_name = last_author_name);
END;

