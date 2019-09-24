package com.mkolongo.cardealer.utils;

import com.mkolongo.cardealer.utils.base.FileUtil;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
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
        List<String> lines = new ArrayList<>();
        fileContext.lines().forEach(lines::add);

        final String path = System.getProperty("user.dir") + "\\src\\main\\resources\\files\\write.json";
        Files.write(Path.of(path), lines, StandardCharsets.UTF_8);
    }
}
