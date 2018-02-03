package com.kelfan.kotlineditor.ui.filepicker

import android.app.Activity
import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kelfan.kotlineditor.R
import com.kelfan.kotlineditor.util.FileWorker
import java.io.File

/**
 * Created by Administrator on 3/02/2018.
 */
class DirectoryFragment : Fragment() {

    private var mEmptyView: View? = null
    private var mPath: String? = null

    private var mFilter: FilterComposite? = null

    private var mDirectoryRecyclerView: RecyclerViewEmpty? = null
    private var mDirectoryAdapter: DirectoryAdapter? = null
    private var mFileClickListener: FileClickListener? = null

    internal interface FileClickListener {
        fun onFileClicked(clickedFile: File)
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        mFileClickListener = activity as FileClickListener
    }

    override fun onDetach() {
        super.onDetach()
        mFileClickListener = null
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle): View? {
        val view = inflater.inflate(R.layout.filepicker_fragement_directory, container, false)
        mDirectoryRecyclerView = view.findViewById<RecyclerViewEmpty>(R.id.filepicker_recycler_view)
        mEmptyView = view.findViewById(R.id.filepicker_empty_view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initArgs()
        initFilesList()
    }

    private fun initFilesList() {
        mDirectoryAdapter = DirectoryAdapter(activity,
                FileWorker.getFileListByDirPath(this!!.mPath!!, this!!.mFilter!!)) // could be problem

        mDirectoryAdapter!!.setOnItemClickListener(object : DirectoryAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                if (mFileClickListener != null) {
                    mFileClickListener!!.onFileClicked(mDirectoryAdapter!!.getModel(position))
                }
            }
        })

        mDirectoryRecyclerView!!.setLayoutManager(LinearLayoutManager(activity))
        mDirectoryRecyclerView!!.setAdapter(mDirectoryAdapter)
        mDirectoryRecyclerView!!.setEmptyView(mEmptyView)
    }

    private fun initArgs() {
        if (arguments.getString(ARG_FILE_PATH) != null) {
            mPath = arguments.getString(ARG_FILE_PATH)
        }

        mFilter = arguments.getSerializable(ARG_FILTER) as FilterComposite
    }

    companion object {

        private val ARG_FILE_PATH = "arg_file_path"
        private val ARG_FILTER = "arg_filter"

        fun getInstance(
                path: String, filter: FilterComposite): DirectoryFragment {
            val instance = DirectoryFragment()

            val args = Bundle()
            args.putString(ARG_FILE_PATH, path)
            args.putSerializable(ARG_FILTER, filter)
            instance.arguments = args

            return instance
        }
    }
}