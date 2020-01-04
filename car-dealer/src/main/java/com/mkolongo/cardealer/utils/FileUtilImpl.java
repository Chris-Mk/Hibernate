package com.mkolongo.cardealer.utils;

import com.mkolongo.cardealer.utils.base.FileUtil;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

@Component
public class FileUtilImpl implements FileUtil {

    @Override
    public String readFile(String filePath) throws IOException {
        return Files.lines(new ClassPathResource(filePath).getFile().toPath())
                .collect(Collectors.joining(System.lineSeparator()));
    }

    @Override
    public void writeFile(String fileContext, String filePath) throws IOException {
        final String path = new ClassPathResource(filePath).getPath();
        Files.write(Path.of(path), fileContext.lines().collect(Collectors.toList()), StandardCharsets.UTF_8);
    }
}
