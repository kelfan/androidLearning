package com.example.jzheng4.filepickermaterialkotlin.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.jzheng4.filepickermaterialkotlin.R
import com.example.jzheng4.filepickermaterialkotlin.utils.FileTypeUtils
import java.io.File

/**
 * Created by jzheng4 on 5/03/2018.
 */
class DirectoryAdapter(private val mContext: Context, private val mFiles: List<File>) : RecyclerView.Adapter<DirectoryAdapter.DirectoryViewHolder>() {
    private var mOnItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    inner class DirectoryViewHolder(itemView: View, clickListener: OnItemClickListener) : RecyclerView.ViewHolder(itemView) {
        internal val mFileImage: ImageView
        internal val mFileTitle: TextView
        internal val mFileSubtitle: TextView

        init {

            itemView.setOnClickListener { v -> clickListener.onItemClick(v, adapterPosition) }

            mFileImage = itemView.findViewById<ImageView>(R.id.item_file_image) as ImageView
            mFileTitle = itemView.findViewById<TextView>(R.id.item_file_title) as TextView
            mFileSubtitle = itemView.findViewById<TextView>(R.id.item_file_subtitle) as TextView
        }
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mOnItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): DirectoryViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_file, parent, false)

        return DirectoryViewHolder(view, this.mOnItemClickListener!!)
    }

    override fun onBindViewHolder(holder: DirectoryViewHolder, position: Int) {
        val currentFile = mFiles[position]

        val fileType = FileTypeUtils.getFileType(currentFile)
        holder.mFileImage.setImageResource(fileType.icon)
        holder.mFileSubtitle.setText(fileType.description)
        holder.mFileTitle.text = currentFile.name
    }

    override fun getItemCount(): Int {
        return mFiles.size
    }

    fun getModel(index: Int): File {
        return mFiles[index]
    }
}