package com.kelfan.textfiler;

import com.kelfan.utillibrary.FileWorker;
import com.kelfan.utillibrary.StringWorker;

public class TextParser {

    private String[] delimiter = {"\n"};
    private String[] levels = {};

    public static String getFileText(String filepath) {
        return FileWorker.readSmallTxtFile(filepath);
    }

    public static String getPreFormat(String indicator) {
        return String.format("<%s>\n", indicator);
    }

    public static String getPosFormat(String indicator) {
        return String.format("\n</%s>\n", indicator);
    }

    public static String getXmlContent(String textIn, String indicator) {
        String pre = getPreFormat(indicator);
        String pos = getPosFormat(indicator);
        if (textIn.contains(pre)) {
            return StringWorker.getBetween(textIn, pre, pos);
        }
        return "";
    }

    public static String setXmlContent(String textIn, String indicator) {
        String pre = getPreFormat(indicator);
        String pos = getPosFormat(indicator);
        return pre.concat(textIn).concat(pos);

    }

}
