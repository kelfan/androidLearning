package com.kelfan.kotlineditor.filter

import java.io.File
import java.io.FileFilter
import java.io.Serializable

/**
 * Created by Administrator on 2/02/2018.
 */
class FilterHidden : FileFilter, Serializable {

    override fun accept(f: File): Boolean {
        return !f.isHidden
    }
}