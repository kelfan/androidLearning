package com.kelfan.kotlineditor.util

import android.text.SpannableString
import android.text.TextUtils
import android.widget.EditText
import android.widget.TextView
import java.util.*

/**
 * Created by Administrator on 21/01/2018.
 */

class EdittextHandler {

    companion object{
        fun todoHandle(instr: String): CharSequence {
            val strList = StringWorker.stringToList(instr, "\n")
            var result: CharSequence = SpannableString("")
//        for (s in strList) {
            strList.forEach{ t ->
                var s = t
                // all string command codes can change to Constant files for change or setting
                s = s.replace(".rq", TimeWorker.getDate()) // get Current time
                s = s.replace(".dt", TimeWorker.getDatetime()) // get Current datetime
                s = s.replace(".mon", TimeWorker.getNextWeekday(Calendar.MONDAY))
                s = s.replace(".tue", TimeWorker.getNextWeekday(Calendar.TUESDAY))
                s = s.replace(".wed", TimeWorker.getNextWeekday(Calendar.WEDNESDAY))
                s = s.replace(".thu", TimeWorker.getNextWeekday(Calendar.THURSDAY))
                s = s.replace(".fri", TimeWorker.getNextWeekday(Calendar.FRIDAY))
                s = s.replace(".sat", TimeWorker.getNextWeekday(Calendar.SATURDAY))
                s = s.replace(".sun", TimeWorker.getNextWeekday(Calendar.SUNDAY))
                s = s.replace(".nmon", TimeWorker.getNextWeekday(7 + Calendar.MONDAY))
                s = s.replace(".ntue", TimeWorker.getNextWeekday(7 + Calendar.TUESDAY))
                s = s.replace(".nwed", TimeWorker.getNextWeekday(7 + Calendar.WEDNESDAY))
                s = s.replace(".nthu", TimeWorker.getNextWeekday(7 + Calendar.THURSDAY))
                s = s.replace(".nfri", TimeWorker.getNextWeekday(7 + Calendar.FRIDAY))
                s = s.replace(".nsat", TimeWorker.getNextWeekday(7 + Calendar.SATURDAY))
                s = s.replace(".nsun", TimeWorker.getNextWeekday(7 + Calendar.SUNDAY))
                s = s.replace(".nnmon", TimeWorker.getNextWeekday(14 + Calendar.MONDAY))
                s = s.replace(".nntue", TimeWorker.getNextWeekday(14 + Calendar.TUESDAY))
                s = s.replace(".nnwed", TimeWorker.getNextWeekday(14 + Calendar.WEDNESDAY))
                s = s.replace(".nnthu", TimeWorker.getNextWeekday(14 + Calendar.THURSDAY))
                s = s.replace(".nnfri", TimeWorker.getNextWeekday(14 + Calendar.FRIDAY))
                s = s.replace(".nnsat", TimeWorker.getNextWeekday(14 + Calendar.SATURDAY))
                s = s.replace(".nnsun", TimeWorker.getNextWeekday(14 + Calendar.SUNDAY))
                s = s.replace(".dqt", TimeWorker.getNextDay(-3)) //大前天
                s = s.replace(".qt", TimeWorker.getNextDay(-2)) //前天
                s = s.replace(".zt", TimeWorker.getNextDay(-1)) // 昨天
                s = s.replace(".jt", TimeWorker.getNextDay(0)) // 今天
                s = s.replace(".mt", TimeWorker.getNextDay(1)) //明天
                s = s.replace(".ht", TimeWorker.getNextDay(2)) //后天
                s = s.replace(".dht", TimeWorker.getNextDay(3)) //大后天

                // quick actions to symbols
                if (s.contains("，，")) {
                    s = "，   ," + TimeWorker.getDatetime() + "," + s.replace("，，", "")
                }
                if (s.contains("。。")) {
                    s = "  " + s.replace("。。", "")
                }
                if (s.contains("？？")) {
                    s = "？ ," + TimeWorker.getDatetime() + "," + s.replace("？？", "")
                }
                if (s.contains("！！")) {
                    s = "！ " + s.replace("！！", "")
                }
                if (s.contains("//1")) {
                    s = "1 " + s.replace("//1", "")
                }
                if (s.contains("//2")) {
                    s = "2 " + s.replace("//2", "")
                }
                if (s.contains("//3")) {
                    s = "3 " + s.replace("//3", "")
                }
                if (s.contains("//4")) {
                    s = "4 " + s.replace("//4", "")
                }
                if (s.contains("//5")) {
                    s = "5 " + s.replace("//5", "")
                }
                if (s.contains("//6")) {
                    s = "6 " + s.replace("//6", "")
                }
                if (s.contains("//7")) {
                    s = "7 " + s.replace("//7", "")
                }
                if (s.contains("//8")) {
                    s = "8 " + s.replace("//8", "")
                }
                if (s.contains("//9")) {
                    s = "9 " + s.replace("//9", "")
                }


                var ss: CharSequence = SpannableString(s)

                // change color based on start symbol
                ss = addStyle(ss, "！", ColorWorker.PURPLE_LIGHT)
                ss = addStyle(ss, "。", ColorWorker.YELLOW_DEEP)
                ss = addStyle(ss, "1", ColorWorker.BROWN_WOOD)
                ss = addStyle(ss, "2", ColorWorker.RED_CORAL)
                ss = addStyle(ss, "3", ColorWorker.YELLOW_LIGHT)
                ss = addStyle(ss, "4", ColorWorker.GREEN_FOREST)
                ss = addStyle(ss, "5", ColorWorker.BLUE_LIGHT)
                ss = addStyle(ss, "6", ColorWorker.BLUE_SEA)
                ss = addStyle(ss, "7", ColorWorker.BLUE_DEEP)
                ss = addStyle(ss, "8", ColorWorker.PURPLE_LIGHT)
                ss = addStyle(ss, "9", ColorWorker.PURPLE_DEEP)
                ss = addStyle(ss, " ", ColorWorker.ORANGE)
                //            ss = addLevel(ss, ",", ColorWorker.GREEN_GRASS);

                // add backgrounds to lines
                ss = addLevel(ss, "/", ColorWorker.GREEN_GRASS)
                ss = addBackground(ss, "()", ColorWorker.BLUE)
                ss = addBackground(ss, "<>", ColorWorker.BLUE)
                ss = addBackground(ss, "[]", ColorWorker.BLUE)
                ss = addBackground(ss, "{}", ColorWorker.BLUE)
                ss = addStyle(ss, "\\？", ColorWorker.BLUE_VERY_LIGHT)
                ss = addStyle(ss, "，", ColorWorker.YELLOW_VERY_LIGHT)
                result = TextUtils.concat(result, ss, SpannableString("\n"))
            }
            return result
        }


        fun addStyle(cha: CharSequence, startStr: String, color: Int): CharSequence {
            val regularCondition = String.format("%s.*", startStr)
            return addCondition(cha, regularCondition, "setTextColor", color, null)
        }

        fun addLevel(cha: CharSequence, s1: String, color: Int): CharSequence {
            val regularCondition = String.format(".*\\%s.*", s1)
            return addCondition(cha, regularCondition, "setLevel", color, s1)
        }

        fun addBackground(cha: CharSequence, range: String, color: Int): CharSequence {
            val regularCondition = String.format(".*\\%s.*\\%s.*", range[0], range[1])//.*\{.*\}.*
            return addCondition(cha, regularCondition, "setBackground", color, range)
        }

        fun addCondition(cha: CharSequence, regularCondition: String, methodName: String, color: Int, condition: String?): CharSequence {
            var cha = cha
            if (cha.toString().matches(regularCondition.toRegex())) {
                try {
                    val m = StringStyleWorker::class.java.getMethod(
                            methodName,
                            String::class.java, Int::class.javaPrimitiveType, String::class.java)
                    val s = m.invoke(null, cha.toString(), color, condition) as CharSequence
                    cha = s
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
            return cha
        }


        fun addLineNumber(editText: EditText, textView: TextView, minLines: Int) {
            // add lines numbers in textView align with editText
            var lines = editText.lineCount
            if (lines <= minLines) {
                lines = minLines
            }
            val layout = editText.layout
            val linesText = StringBuilder()
            var count = 1
            for (z in 1..lines) {
                if (layout != null && z > 1) {
                    val curIndex = layout.getLineEnd(z - 2)
                    val end = editText.text[curIndex - 1]
                    if (end != '\n') {
                        linesText.append("*").append("\n")
                    } else {
                        linesText.append(count).append("\n")
                        count++
                    }
                } else {
                    linesText.append(count).append("\n")
                    count++
                }
            }
            textView.text = linesText.toString()
        }
    }
}
