package com.mkolongo.judgesystem.util.base;

import java.io.IOException;

public interface FileUtil {

    String readFile(String filePath) throws IOException;

    void writeFile(String filePath, String fileContents) throws IOException;
}
