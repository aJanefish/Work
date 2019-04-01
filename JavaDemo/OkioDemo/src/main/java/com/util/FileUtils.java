package com.util;

import java.io.File;

public class FileUtils {
    private static final String PATH = "data/";

    public static File newFile(String name) {
        return new File(PATH + name);
    }
}
