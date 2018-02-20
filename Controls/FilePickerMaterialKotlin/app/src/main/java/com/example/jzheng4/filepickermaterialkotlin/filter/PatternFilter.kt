package com.example.jzheng4.filepickermaterialkotlin.filter

import java.io.File
import java.io.FileFilter
import java.io.Serializable
import java.util.regex.Pattern

/**
 * Created by jzheng4 on 20/02/2018.
 */
class PatternFilter(private val mPattern: Pattern, private val mDirectoriesFilter: Boolean) : FileFilter, Serializable {

    override fun accept(f: File): Boolean {
        return f.isDirectory && !mDirectoriesFilter || mPattern.matcher(f.name).matches()
    }
}