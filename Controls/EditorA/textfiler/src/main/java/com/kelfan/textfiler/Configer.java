package com.kelfan.textfiler;

import com.kelfan.utillibrary.StringWorker;

public class Configer {

    public static String indicator_config = "config";
    public static String indicator_delimiter = "delimiter";
    public static String indicator_level = "level";
    public static String split_config = " ";

    private String[] delimiter = {"\n"};
    private String[] levels = {};

    public Configer() {
    }

    public Configer(String delimiter) {
        this.delimiter = delimiter.split(split_config);
    }

    public Configer(String[] delimiter) {
        this.delimiter = delimiter;
    }

    public Configer(String[] delimiter, String[] levels) {
        this.delimiter = delimiter;
        this.levels = levels;
    }

    public String[] getDelimiter() {

        return delimiter;
    }

    public Configer setDelimiter(String[] delimiter) {
        this.delimiter = delimiter;
        return this;
    }

    public String[] getLevels() {
        return levels;
    }

    public Configer setLevels(String[] levels) {
        this.levels = levels;
        return this;
    }

    @Override
    public String toString() {
        String delimiterStr = TextParser.setXmlContent(StringWorker.list2str(delimiter, split_config), indicator_delimiter);
        String levelStr = TextParser.setXmlContent(StringWorker.list2str(levels, split_config), indicator_level);
        return String.format("%s%s", delimiterStr, levelStr);
    }
}
