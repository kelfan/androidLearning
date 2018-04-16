package com.kelfan.logfiler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Administrator on 1/01/2018.
 */

public class StringWorker {
    /**
     * transform a String into a list based on
     * @param inString A String
     * @param delimiter The delimiter for splitting text
     * @return a list
     */
    public static ArrayList<String> stringToList(String inString, String delimiter) {
        return new ArrayList<>(Arrays.asList(inString.split(delimiter)));
    }

    public static ArrayList<String> stringToListByLine(String inString) {
        return stringToList(inString, "\n");
    }

    public static String stringSortByLine(String inString, boolean desc) {
        ArrayList<String> list = stringToListByLine(inString);
        if (!desc) {
            Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
        } else {
            Collections.sort(list, Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER));
        }
        return listToStringByLine(list);
    }

    /**
     * transform List of String into a single String
     * @param inList list of String
     * @return a single String
     */
    public static String listToString(ArrayList<String> inList,String delimiter) {
        String listString = "";
        for (String s : inList)
        {
            listString += s + delimiter;
        }
        return listString;
    }

    public static String listToStringByLine(ArrayList<String> inList) {
        return listToString(inList, "\n");
    }

    public static String replaceByLine(String inStr, String supersede, String replace) {
        return inStr.replace(supersede, replace);
    }

    public static long countLetter(String inStr, String letter) {
        return (inStr.length() - inStr.replace(letter, "").length()) / letter.length();
    }

    public static String getLast2end(String inStr, String delimiter) {
        if (inStr.contains(delimiter)) {
            if (delimiter.equals(".")){
                delimiter = "[.]";
            }
            String[] txtList = inStr.split(delimiter);
            String result = txtList[txtList.length-1];
            return result;
        }
        return inStr;
    }
}
