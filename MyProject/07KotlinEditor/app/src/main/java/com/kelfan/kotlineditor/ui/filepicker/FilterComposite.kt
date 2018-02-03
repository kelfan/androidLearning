package com.kelfan.kotlineditor.ui.filepicker

import java.io.File
import java.io.FileFilter
import java.io.Serializable
import java.util.ArrayList

/**
 * Created by Administrator on 2/02/2018.
 */

class FilterComposite(private val fileFilters: ArrayList<FileFilter>) : FileFilter, Serializable {

    override fun accept(file: File): Boolean {
        for (filter in fileFilters) {
            if (!filter.accept(file)) {
                return false
            }
        }

        return true
    }
}
