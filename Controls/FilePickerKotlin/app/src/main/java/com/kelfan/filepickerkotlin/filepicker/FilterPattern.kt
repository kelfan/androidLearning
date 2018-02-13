package com.kelfan.kotlineditor.ui.filepicker

import java.io.File
import java.io.FileFilter
import java.io.Serializable
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2/02/2018.
 */
class FilterPattern (private val mPattern: Pattern, private val mDirectoriesFilter: Boolean) : FileFilter, Serializable {

    override fun accept(f: File): Boolean {
        return f.isDirectory && !mDirectoriesFilter || mPattern.matcher(f.name).matches()
    }
}