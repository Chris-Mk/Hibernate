package com.mkolongo.judgesystem.util;

import com.mkolongo.judgesystem.util.base.FileUtil;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class FileUtilImpl implements FileUtil {

    @Override
    public String readFile(String filePath) throws IOException {
        return Files.lines(Path.of(new ClassPathResource(filePath).getFile().getPath()))
                .collect(Collectors.joining(System.lineSeparator()));
    }

    @Override
    public void writeFile(String filePath, String fileContents) throws IOException {
        Files.write(
                Path.of(new ClassPathResource(filePath).getFile().getPath()),
                fileContents.lines().collect(Collectors.toList())
        );
    }
}
