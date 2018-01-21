package com.kelfan.kotlineditor.util

import java.util.*

/**
 * Created by Administrator on 21/01/2018.
 */
class StringWorker {
    companion object{
        /**
         * transform a String into a list based on
         * @param inString A String
         * @param delimiter The delimiter for splitting text
         * @return a list
         */
        fun stringToList(inString: String, delimiter: String): ArrayList<String> {
            return ArrayList(Arrays.asList(*inString.split(delimiter.toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()))
        }

        fun stringToListByLine(inString: String): ArrayList<String> {
            return stringToList(inString, "\n")
        }

        fun stringSortByLine(inString: String, desc: Boolean): String {
            val list = stringToListByLine(inString)
            if (!desc) {
                Collections.sort(list, String.CASE_INSENSITIVE_ORDER)
            } else {
                Collections.sort(list, Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER))
            }
            return listToStringByLine(list)
        }

        /**
         * transform List of String into a single String
         * @param inList list of String
         * @return a single String
         */
        fun listToString(inList: ArrayList<String>, delimiter: String): String {
            var listString = ""
            for (s in inList) {
                listString += s + delimiter
            }
            return listString
        }

        fun listToStringByLine(inList: ArrayList<String>): String {
            return listToString(inList, "\n")
        }

        fun replaceByLine(inStr: String, supersede: String, replace: String): String {
            return inStr.replace(supersede, replace)
        }

        fun countLetter(inStr: String, letter: String): Long {
            return ((inStr.length - inStr.replace(letter, "").length) / letter.length).toLong()
        }
    }
}
