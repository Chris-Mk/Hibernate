package com.mkolongo.bookshopsystem.services;

import com.mkolongo.bookshopsystem.entities.Author;
import com.mkolongo.bookshopsystem.repositories.AuthorRepository;
import com.mkolongo.bookshopsystem.services.base.AuthorService;
import com.mkolongo.bookshopsystem.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AuthorServiceImpl implements AuthorService {
    private static final String FILE_PATH = System.getProperty("user.dir")
            + "\\src\\main\\resources\\files\\authors.txt";

    private final AuthorRepository authorRepository;
    private final FileUtil fileUtil;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, FileUtil fileUtil) {
        this.authorRepository = authorRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedAuthors() throws IOException {
        if (this.authorRepository.count() != 0) return;

        final String[] authorsContentFile = fileUtil.getFileContent(FILE_PATH);

        for (String authorNames : authorsContentFile) {
            String[] names = authorNames.split("\\s+");

            Author author = new Author();
            author.setFirstName(names[0]);
            author.setLastName(names[1]);

            this.authorRepository.saveAndFlush(author);
        }
    }
}
