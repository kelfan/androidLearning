package com.kelfan.kotlineditor.util

import android.content.Context

/**
 * Created by Administrator on 21/01/2018.
 */

class FileHandler {
    companion object{
        fun read_data_file(filename: String): String {
            return FileWorker.readStringFromSecondSD(Constant.DEFAULT_FILE_PATH, filename)
        }

        fun write_data_file(filename: String, txt: String): Int {
            return FileWorker.writeToSecondSD(Constant.DEFAULT_FILE_PATH, filename, txt)
        }

        fun read_app_file(context: Context, filename: String): String {
            val file:String = FileWorker.getAppPath(context, Constant.DIRECTORY_DATA)!!
            return FileWorker.readAllText(file)
        }
    }
}