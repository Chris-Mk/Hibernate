package com.mkolongo.productsshop.util;

import com.mkolongo.productsshop.util.base.FileUtil;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

@Component
public class FileUtilImpl implements FileUtil {

    @Override
    public String readFile(String sourcePath) throws IOException {
        final File file = new ClassPathResource(sourcePath).getFile();

        Scanner scanner = new Scanner(new FileReader(file));
        StringBuilder builder = new StringBuilder();

        while (scanner.hasNext()) {
            builder.append(scanner.nextLine())
                    .append(System.lineSeparator());
        }

        return builder.toString().trim();
    }
}
