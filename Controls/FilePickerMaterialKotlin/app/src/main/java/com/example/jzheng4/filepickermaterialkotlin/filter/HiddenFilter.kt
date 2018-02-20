package com.example.jzheng4.filepickermaterialkotlin.filter

import java.io.File
import java.io.FileFilter
import java.io.Serializable

/**
 * Created by jzheng4 on 20/02/2018.
 */
class HiddenFilter : FileFilter, Serializable {

    override fun accept(f: File): Boolean {
        return !f.isHidden
    }
}
