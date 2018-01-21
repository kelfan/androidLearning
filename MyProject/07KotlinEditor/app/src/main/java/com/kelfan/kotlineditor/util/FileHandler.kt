package com.kelfan.kotlineditor.util

/**
 * Created by Administrator on 21/01/2018.
 */

object FileHandler {

    fun read_app_file(filename: String): String {
        return FileWorker.readStringFromSecondSD(Constant.DEFAULT_FILE_PATH, filename)
    }

    fun write_app_file(filename: String, txt: String): Int {
        return FileWorker.writeToSecondSD(Constant.DEFAULT_FILE_PATH, filename, txt)
    }

}