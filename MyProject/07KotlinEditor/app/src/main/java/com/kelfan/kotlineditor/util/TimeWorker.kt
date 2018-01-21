package com.kelfan.kotlineditor.util

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Administrator on 21/01/2018.
 */
class TimeWorker {

    companion object{
        fun getDate():String{
            return SimpleDateFormat("yyyy-MM-dd").format(Date())
        }

        fun getDatetime():String{
            return SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date())
        }

        fun getNextWeekday(weekday: Int): String {
            val now = Calendar.getInstance()
            val cWeekday = now.get(Calendar.DAY_OF_WEEK)
            var difference = weekday - cWeekday
            if (difference <= 0) {
                difference += 7
            }
            now.add(Calendar.DAY_OF_YEAR, difference)
            val date = now.time
            return SimpleDateFormat(Constant.DAYWEEKFORMAT).format(date)
        }


        fun getNextDay(days: Int): String {
            val now = Calendar.getInstance()
            now.add(Calendar.DAY_OF_YEAR, days)
            val date = now.time
            return SimpleDateFormat(Constant.DAYWEEKFORMAT).format(date)
        }
    }


}
