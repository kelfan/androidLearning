package com.example.jzheng4.filepickermaterialkotlin.utils

import java.io.File
import java.io.FileFilter
import java.text.DecimalFormat
import java.util.*

/**
 * Created by jzheng4 on 20/02/2018.
 */
object FileUtils {
    fun getFileListByDirPath(path: String, filter: FileFilter): List<File> {
        val directory = File(path)
        val files = directory.listFiles(filter) ?: return ArrayList()

        val result = Arrays.asList(*files)
        Collections.sort(result, FileComparator())
        return result
    }

    fun cutLastSegmentOfPath(path: String): String {
        if (path.length - path.replace("/", "").length <= 1)
            return "/"
        var newPath = path.substring(0, path.lastIndexOf("/"))
        // We don't need to list the content of /storage/emulated
        if (newPath == "/storage/emulated")
            newPath = "/storage"
        return newPath
    }

    fun getReadableFileSize(size: Long): String {
        if (size <= 0) return "0"
        val units = arrayOf("B", "KB", "MB", "GB", "TB")
        val digitGroups = (Math.log10(size.toDouble()) / Math.log10(1024.0)).toInt()
        return DecimalFormat("#").format(size / Math.pow(1024.0, digitGroups.toDouble())) + " " + units[digitGroups]
    }
}