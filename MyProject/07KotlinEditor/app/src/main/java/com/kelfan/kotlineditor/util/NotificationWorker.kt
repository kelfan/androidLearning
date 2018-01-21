package com.kelfan.kotlineditor.util

import android.content.Context
import android.widget.Toast

/**
 * Created by Administrator on 21/01/2018.
 */
class NotificationWorker {
    companion object{
        fun notifyResult(context: Context, action: String, result: Int) {
            if (result == 1) {
                Toast.makeText(context, action + " success", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, action + " fail", Toast.LENGTH_SHORT).show()
            }
        }
    }
}