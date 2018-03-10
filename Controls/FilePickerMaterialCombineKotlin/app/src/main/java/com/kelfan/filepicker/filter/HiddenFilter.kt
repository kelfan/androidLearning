package com.kelfan.filepicker.filter

import java.io.File
import java.io.FileFilter
import java.io.Serializable

/**
 * Created by Administrator on 7/03/2018.
 */
class HiddenFilter : FileFilter, Serializable {

    override fun accept(f: File): Boolean {
        return !f.isHidden
    }
}
