package com.mkolongo.cardealer.utils.base;

import java.io.IOException;

public interface FileUtil {

    String readFile(String filePath) throws IOException;

    void writeFile(String fileContext, String filePath) throws IOException;
}
