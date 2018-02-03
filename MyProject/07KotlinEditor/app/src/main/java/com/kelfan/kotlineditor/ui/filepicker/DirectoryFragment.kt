package com.kelfan.kotlineditor.ui.filepicker

import android.app.Fragment
import java.io.File

/**
 * Created by Administrator on 3/02/2018.
 */
class DirectoryFragment : Fragment() {
    interface FileClickListener {
        fun onFileClicked(clickedFile :File)
    }

    private val ARG_FILE_PATH = "arg_file_path"
    private val ARG_FILTER = "arg_filter"


}