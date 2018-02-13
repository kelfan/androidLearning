package com.kelfan.kotlineditor.ui.filepicker

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View

/**
 * Created by Administrator on 3/02/2018.
 */
class RecyclerViewEmpty : RecyclerView {
    internal var mEmptyView: View? = null

    internal val observer: RecyclerView.AdapterDataObserver = object : RecyclerView.AdapterDataObserver() {
        override fun onChanged() {
            super.onChanged()
            checkIfEmpty()
        }
    }

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {}

    internal fun checkIfEmpty() {
        if (mEmptyView != null) {
            mEmptyView!!.visibility = if (adapter.itemCount > 0) View.GONE else View.VISIBLE
        }
    }

    override fun setAdapter(adapter: RecyclerView.Adapter<*>?) {
        val oldAdapter = getAdapter()
        oldAdapter?.unregisterAdapterDataObserver(observer)
        super.setAdapter(adapter)
        adapter?.registerAdapterDataObserver(observer)
    }

    fun setEmptyView(mEmptyView: View?) {
        this.mEmptyView = mEmptyView
        checkIfEmpty()
    }
}
