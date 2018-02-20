package com.example.jzheng4.filepickermaterialkotlin.filter

import java.io.File
import java.io.FileFilter
import java.io.Serializable
import java.util.ArrayList

/**
 * Created by jzheng4 on 20/02/2018.
 */
class CompositeFilter(private val mFilters: ArrayList<FileFilter>) : FileFilter, Serializable {

    override fun accept(f: File): Boolean {
        for (filter in mFilters) {
            if (!filter.accept(f)) {
                return false
            }
        }

        return true
    }
}
