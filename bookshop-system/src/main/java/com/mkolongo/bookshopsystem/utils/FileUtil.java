package com.mkolongo.bookshopsystem.utils;

import java.io.IOException;

public interface FileUtil {

    String[] getFileContent(String filePath) throws IOException;
}
