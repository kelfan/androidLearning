package Util;

import com.kelfan.tditor.Constant;

/**
 * Created by Administrator on 15/01/2018.
 */

public class FileHandler {

    public static String read_app_file(String filename) {
        return FileWorker.readStringFromSD(Constant.DEFAULT_FILE_PATH, filename);
    }

    public static int write_app_file(String filename, String txt) {
        return FileWorker.writeToSD(Constant.DEFAULT_FILE_PATH, filename, txt);
    }

}
