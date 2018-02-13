package com.kelfan.kotlineditor.ui.filepicker

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.kelfan.kotlineditor.R
import java.io.File

/**
 * Created by Administrator on 2/02/2018.
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

            mFileImage = itemView.findViewById<ImageView>(R.id.filepicker_item_image)
            mFileTitle = itemView.findViewById<TextView>(R.id.filepicker_item_title)
            mFileSubtitle = itemView.findViewById<TextView>(R.id.filepicker_item_subtitle)
        }
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mOnItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): DirectoryViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.filepicker_item, parent, false)

        return DirectoryViewHolder(view, this!!.mOnItemClickListener!!)
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