package com.mkolongo.realestateagency.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Collectors;

@Component
public class FileUtilImpl implements FileUtil {

    @Override
    public String readFile(String filePath) throws IOException {
        return Files.lines(new ClassPathResource(filePath).getFile().toPath())
                .collect(Collectors.joining());
    }
}
