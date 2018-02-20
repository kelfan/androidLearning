package com.example.jzheng4.filepickermaterialkotlin.utils

import java.io.File
import java.util.Comparator

/**
 * Created by jzheng4 on 20/02/2018.
 */
class FileComparator : Comparator<File> {
    override fun compare(f1: File, f2: File): Int {
        if (f1 === f2) {
            return 0
        }
        if (f1.isDirectory && f2.isFile) {
            // Show directories above files
            return -1
        }
        return if (f1.isFile && f2.isDirectory) {
            // Show files below directories
            1
        } else f1.name.compareTo(f2.name, ignoreCase = true)
        // Sort the directories alphabetically
    }
}