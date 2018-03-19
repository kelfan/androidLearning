package com.kelfan.editora.util;

import java.io.File;

/**
 * Created by Administrator on 14/03/2018.
 */

public class FileConfiger extends FileWorker {
    public static final String CONFIG_PATH = "/Editor";
    public static final String CONFIG_FILE = "config.txt";

    public static String readConfig() {
        File path = FileWorker.getStoragePath(FileWorker.STORAGE_EXTERNAL, CONFIG_PATH + "/" + CONFIG_FILE);
        if (!path.exists()) {
            FileWorker.writeToSD(CONFIG_PATH, CONFIG_FILE, "");
        }
        return FileWorker.readStringFromSD(CONFIG_PATH, CONFIG_FILE);
    }

    public static void writeConfig(String sContent) {
        FileWorker.writeToSD(CONFIG_PATH, CONFIG_FILE, sContent);
    }
}
