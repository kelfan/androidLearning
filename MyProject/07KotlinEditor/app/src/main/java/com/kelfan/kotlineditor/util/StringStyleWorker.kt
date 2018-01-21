package com.kelfan.kotlineditor.util

import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextUtils
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan

/**
 * Created by Administrator on 21/01/2018.
 */
class StringStyleWorker {
    companion object{
        fun setTextColor(inStr: String, color: Int, s2: String): SpannableString {
            val s = SpannableString(inStr)
            s.setSpan(ForegroundColorSpan(color),
                    0, inStr.length, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
            s.setSpan(BackgroundColorSpan(color), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            return s
        }

        fun setBackground(inStr: String, color: Int, range: String): SpannableString {
            val s = SpannableString(inStr)
            val pos1 = inStr.toLowerCase().indexOf(range.toLowerCase()[0])
            val pos2 = inStr.toLowerCase().indexOf(range.toLowerCase()[1]) + 1
            s.setSpan(ForegroundColorSpan(ColorWorker.getComplimentColor(color)),
                    pos1, pos2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            s.setSpan(BackgroundColorSpan(color), pos1, pos2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            return s
        }

        fun setLevel(inStr: String, color: Int, delimiter: String): CharSequence {
            var color = color
            val sList = inStr.split(delimiter.toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            var result: CharSequence = SpannableString("")
            for (i in 0 until sList.size - 1) {
                val s = SpannableString(sList[i] + delimiter)
                s.setSpan(ForegroundColorSpan(ColorWorker.getComplimentColor(color)),
                        0, sList[i].length + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                s.setSpan(BackgroundColorSpan(color), 0, sList[i].length + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                color = ColorWorker.getComplimentColor(color)
                result = TextUtils.concat(result, s)
            }
            result = TextUtils.concat(result, sList[sList.size - 1])
            return result
        }

        fun backgroundYellow(inStr: String): SpannableString {
            val s = SpannableString(inStr)
            s.setSpan(BackgroundColorSpan(ColorWorker.YELLOW_DEEP), 0, inStr.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            return s
        }
    }
}
