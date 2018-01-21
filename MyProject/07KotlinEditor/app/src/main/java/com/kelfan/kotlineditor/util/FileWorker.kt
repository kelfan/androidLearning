package com.kelfan.kotlineditor.util

import android.content.Context
import android.os.Environment
import android.util.Log
import java.io.*
import java.util.ArrayList

/**
 * Created by Administrator on 20/01/2018.
 * @notice: require permission to manipulate files
 * @result: 1 means success, 0 means fail
 */



class FileWorker{
    companion object {
        /**
         * read file into string
         * @param sPath path of file
         * @param sFileName filename
         * @param iLocation 0 for data, 1 for external SD storage, 2 for secondary SD storage
         * @return String or "fail" if read file fail
         */
        private fun readFileToString(sPath: String, sFileName: String, iLocation: Int): String {
            try {
                val root = getStoragePath(iLocation, sPath)
                if (!root.exists()) {
                    throw IOException()
                }
                val file = File(root, sFileName)
                if (!file.exists()) {
                    throw IOException()
                }
                val text = StringBuilder()
                val br = BufferedReader(FileReader(file))
                var line: String?
                line = br.readLine()
                while (line != null) {
                    text.append(line)
                    text.append('\n')
                    line = br.readLine()
                }
                br.close()
                return text.toString()
            } catch (e: IOException) {
                e.printStackTrace()
                return ""
            }
        }

        fun readStringFromData(sPath: String, sFileName: String): String {
            return readFileToString(sPath, sFileName, 0)
        }

        fun readStringFromSD(sPath: String, sFileName: String): String {
            return readFileToString(sPath, sFileName, 1)
        }

        fun readStringFromSecondSD(sPath: String, sFileName: String): String {
            return readFileToString(sPath, sFileName, 2)
        }


        /**
         * read file into arraylist
         * @param sPath path of file
         * @param sFileName filename
         * @param iLocation 0 for data, 1 for external SD storage, 2 for secondary SD storage
         * @return String or "fail" if read file fail
         */
        fun readFileToArrayList(sPath: String, sFileName: String, iLocation: Int): ArrayList<*>? {
            try {
                val root = getStoragePath(iLocation, sPath)
                if (!root.exists()) {
                    throw IOException()
                }
                val file = File(root, sFileName)
                val aList = ArrayList<String>()
                val input = BufferedReader(FileReader(file))
                var line: String? // 问号? 定义变量时，可在类型后面加一个问号?，表示该变量是Nullable，不加表示该变量不可为null
                if (!input.ready()) {
                    throw IOException()
                }
                line = input.readLine()
                while (line != null) {
                    aList.add(line)
                    line = input.readLine()
                }
                input.close()
                return aList
            } catch (e: IOException) {
                e.printStackTrace()
                return null
            }
        }

        fun readListFromData(sPath: String, sFileName: String): ArrayList<*>? {
            return readFileToArrayList(sPath, sFileName, 0)
        }

        fun readListFromSD(sPath: String, sFileName: String): ArrayList<*>? {
            return readFileToArrayList(sPath, sFileName, 1)
        }

        fun readListFromSecondSD(sPath: String, sFileName: String): ArrayList<*>? {
            return readFileToArrayList(sPath, sFileName, 2)
        }


        /**
         * function to save string into a file
         * @param sPath path to storage
         * @param sFileName filename
         * @param sBody string to storage
         * @param iLocation 0 for data, 1 for external SD storage, 2 for secondary SD storage
         * @param mode 0 for append, 1 for write
         */
        private fun saveFile(sPath: String, sFileName: String, SBody: String, iLocation: Int, mode: Int): Int {
            try {
                val root: File = getStoragePath(iLocation, sPath)
                if (!root.exists()) {
                    root.mkdirs()
                }
                val outFile: File = File(root, sFileName)
                if (!outFile.exists()) {
                    outFile.createNewFile()
                }
                val writer: FileWriter
                when (mode) {
                    Constant.FILE_WRITE -> {
                        writer = FileWriter(outFile, false)
                        writer.write(SBody)
                    }
                    else -> {
                        writer = FileWriter(outFile, true)
                        writer.append(SBody)
                    }
                }
                writer.flush()
                writer.close()
                return Constant.SUCCESS
            } catch (e: IOException) {
                e.printStackTrace()
                Log.e("APP", "cannot write to file in saveFile method")
                return Constant.FAIL
            }
        }

        fun writeToData(sPath: String, sFileName: String, sBody: String): Int {
            return saveFile(sPath, sFileName, sBody, Constant.STORAGE_DATA, Constant.FILE_WRITE)
        }

        fun writeToSD(sPath: String, sFileName: String, sBody: String): Int {
            return saveFile(sPath, sFileName, sBody, Constant.STORAGE_SD, Constant.FILE_WRITE)
        }

        fun writeToSecondSD(sPath: String, sFileName: String, sBody: String): Int {
            return saveFile(sPath, sFileName, sBody, Constant.STORAGE_SECOND_SD, Constant.FILE_WRITE)
        }

        fun appendToData(sPath: String, sFileName: String, sBody: String): Int {
            return saveFile(sPath, sFileName, sBody, Constant.STORAGE_DATA, Constant.FILE_APPEND)
        }

        fun appendToSD(sPath: String, sFileName: String, sBody: String): Int {
            return saveFile(sPath, sFileName, sBody,  Constant.STORAGE_SD, Constant.FILE_APPEND)
        }

        fun appendToSecondSD(sPath: String, sFileName: String, sBody: String): Int {
            return saveFile(sPath, sFileName, sBody, Constant.STORAGE_SECOND_SD, Constant.FILE_APPEND)
        }

        /**
         * get the Storage Path
         * @param iLocation 0 for data, 1 for external SD storage, 2 for secondary SD storage
         * @return File
         */
        fun getStoragePath(iLocation: Int, sPath: String):  File{
            return when (iLocation) {
                Constant.STORAGE_DATA -> File(Environment.getDataDirectory(), sPath)
                Constant.STORAGE_SD -> File(Environment.getExternalStorageDirectory(), sPath)
                Constant.STORAGE_SECOND_SD -> File(System.getenv("SECONDARY_STORAGE"), sPath)
                else -> {
                    File(Environment.getExternalStorageDirectory(), sPath)
                }
            }
        }

        @Throws(IOException::class)
        fun readAllText(filePath: String): String {
            var br: BufferedReader? = null
            try {
                br = BufferedReader(FileReader(filePath))
                val sb = StringBuilder()
                var line: String? = br.readLine()

                while (line != null) {
                    sb.append(line)
                    sb.append("\n")
                    line = br.readLine()
                }
                br.close()
                return sb.toString()
            } catch (e: Exception) {
                if (br != null) {
                    br.close()
                }
                throw e
            }
        }


        @Throws(IOException::class)
        fun writeAllText(filePath: String, contents: String): Boolean {
            val out = BufferedWriter(OutputStreamWriter(
                    FileOutputStream(filePath), "UTF-8"))
            out.write(contents)
            out.close()
            return true
        }

        fun getFileExtension(fileName: String): String {
            val dotIndex = fileName.lastIndexOf(".")
            if (dotIndex == -1)
                return ""
            return fileName.substring(dotIndex + 1, fileName.length)
        }
    }
}