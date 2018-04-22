package com.kelfan.textfiler;

import com.kelfan.utillibrary.FileWorker;
import com.kelfan.utillibrary.StringWorker;

import static com.kelfan.textfiler.Configer.indicator_config;
import static com.kelfan.textfiler.Configer.indicator_delimiter;
import static com.kelfan.textfiler.Configer.indicator_level;
import static com.kelfan.textfiler.Configer.split_config;

public class TextParser {


    public static String getFileText(String filepath) {
        return FileWorker.readSmallTxtFile(filepath);
    }

    public static String getPreFormat(String indicator) {
        return String.format("<%s>", indicator);
    }

    public static String getPosFormat(String indicator) {
        return String.format("</%s>\n", indicator);
    }

    public static String getXmlContent(String textIn, String indicator) {
        String pre = getPreFormat(indicator);
        String pos = getPosFormat(indicator);
        if (textIn.contains(pre)) {
            return StringWorker.getBetween(textIn, pre, pos).replaceAll("\\\\n", "\n");
        }
        return "";
    }

    public static String setXmlContent(String textIn, String indicator) {
        String pre = getPreFormat(indicator);
        String pos = getPosFormat(indicator);
        textIn = textIn.replaceAll("\n", "\\\\n");
        return pre.concat(textIn).concat(pos);
    }

    public static Configer getConfig(String textIn) {
        String config = getPreFormat(indicator_config);
        Configer configer = new Configer();
        if (textIn.contains(indicator_delimiter)) {
            String delimiterStr = getXmlContent(textIn, indicator_delimiter);
            configer.setDelimiter(delimiterStr.split(split_config));
        }
        if (textIn.contains(indicator_level)){
            configer.setLevels(getXmlContent(textIn, indicator_level).split(split_config));
        }
        return configer;
    }



}
