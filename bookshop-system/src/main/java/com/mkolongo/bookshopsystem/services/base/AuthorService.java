package com.mkolongo.bookshopsystem.services.base;

import java.io.IOException;

public interface AuthorService {

    void seedAuthors() throws IOException;
    
    List<Author> getAuthorsByPattern(String letters);
}
