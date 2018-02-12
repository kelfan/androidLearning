package com.kelfan.fortest.Util;


import com.kelfan.fortest.Constant;

/**
 * Created by Administrator on 15/01/2018.
 */

public class FileHandler {

    public static String read_app_file(String filename) {
        return FileWorker.readStringFromSecondSD(Constant.DEFAULT_FILE_PATH, filename);
    }

    public static int write_app_file(String filename, String txt) {
        return FileWorker.writeToSecondSD(Constant.DEFAULT_FILE_PATH, filename, txt);
    }

    public static int append_app_file(String filename, String txt) {
        return FileWorker.appendToSecondSD(Constant.DEFAULT_FILE_PATH, filename, txt);
    }

}
