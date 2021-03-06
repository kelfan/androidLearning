package com.kelfan.fantexteditor;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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
        if (desc == false) {
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
}
