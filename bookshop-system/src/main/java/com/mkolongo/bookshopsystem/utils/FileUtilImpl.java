package com.mkolongo.bookshopsystem.utils;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class FileUtilImpl implements FileUtil {

    @Override
    public String[] getFileContent(String filePath) throws IOException {
//        File file = new File(filePath);
//        BufferedReader reader = new BufferedReader(new FileReader(file));

        return Files.lines(Path.of(filePath))
                .filter(line -> !line.isBlank())
                .toArray(String[]::new);
    }
}
