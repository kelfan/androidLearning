package com.kelfan.textfiler;

import com.kelfan.utillibrary.FileWorker;
import com.kelfan.utillibrary.StringWorker;

public class TextParser {

    private String[] delimiter = {"\n", ":"};
    private String[] levels = {"!", "\t"};

    public static String getFileText(String filepath){
        return FileWorker.readSmallTxtFile(filepath);
    }

    public static String getXmlContent(String textIn, String indicator){
        String pre = String.format("<%s>\n", indicator);
        String pos = String.format("\n</%s>\n", indicator);
        if (textIn.contains(pre)){
            return StringWorker.getBetween(textIn, pre, pos);
        }
        return "";
    }

    public static String setXmlContent(String textIn, String indicator){
        String pre = String.format("<%s>\n", indicator);
        String pos = String.format("\n</%s>\n", indicator);
        return pre.concat(textIn).concat(pos);

    }

}
