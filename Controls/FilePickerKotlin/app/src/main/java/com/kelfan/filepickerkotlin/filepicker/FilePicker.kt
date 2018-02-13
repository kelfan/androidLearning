package com.kelfan.kotlineditor.ui.filepicker

import android.app.Activity
import android.app.Fragment
import android.content.Intent
import java.io.FileFilter
import java.util.ArrayList
import java.util.regex.Pattern

/**
 * Created by Administrator on 3/02/2018.
 * file picker builder
 */


class FilePicker {
    private var mActivity: Activity? = null
    private var mFragment: Fragment? = null
    private var mSupportFragment: android.support.v4.app.Fragment? = null

    private var mFilePickerClass: Class<out FilePickerActivity> = FilePickerActivity::class.java

    private var mRequestCode: Int? = null
    private var mFileFilter: Pattern? = null
    private var mDirectoriesFilter: Boolean? = false
    private var mRootPath: String? = null
    private var mCurrentPath: String? = null
    private var mShowHidden: Boolean? = false
    private var mCloseable: Boolean? = true
    private var mTitle: CharSequence? = null

    val filter: FilterComposite
        get() {
            val filters = ArrayList<FileFilter>()

            if ((!mShowHidden!!)!!) {
                filters.add(FilterHidden())
            }

            if (mFileFilter != null) {
                filters.add(FilterPattern(this.mFileFilter!!, this!!.mDirectoriesFilter!!))
            }

            return FilterComposite(filters)
        }


    /**
     * @return Intent that can be used to start Material File Picker
     */
    val intent: Intent
        get() {
            val filter = filter

            var activity: Activity? = null
            if (mActivity != null) {
                activity = mActivity
            } else if (mFragment != null) {
                activity = mFragment!!.activity
            } else if (mSupportFragment != null) {
                activity = mSupportFragment!!.activity
            }

            val intent = Intent(activity, mFilePickerClass)
            intent.putExtra(FilePickerActivity.ARG_FILTER, filter)
            intent.putExtra(FilePickerActivity.ARG_CLOSEABLE, mCloseable)

            if (mRootPath != null) {
                intent.putExtra(FilePickerActivity.ARG_START_PATH, mRootPath)
            }

            if (mCurrentPath != null) {
                intent.putExtra(FilePickerActivity.ARG_CURRENT_PATH, mCurrentPath)
            }

            if (mTitle != null) {
                intent.putExtra(FilePickerActivity.ARG_TITLE, mTitle)
            }

            return intent
        }


    /**
     * Specifies activity, which will be used to
     * start file picker
     */
    fun withActivity(activity: Activity): FilePicker {
        if (mSupportFragment != null || mFragment != null) {
            throw RuntimeException("You must pass either Activity, Fragment or SupportFragment")
        }

        mActivity = activity
        return this
    }

    /**
     * Specifies fragment, which will be used to
     * start file picker
     */
    fun withFragment(fragment: Fragment): FilePicker {
        if (mSupportFragment != null || mActivity != null) {
            throw RuntimeException("You must pass either Activity, Fragment or SupportFragment")
        }

        mFragment = fragment
        return this
    }

    /**
     * Specifies support fragment which will be used to
     * start file picker
     */
    fun withSupportFragment(fragment: android.support.v4.app.Fragment): FilePicker {
        if (mActivity != null || mFragment != null) {
            throw RuntimeException("You must pass either Activity, Fragment or SupportFragment")
        }

        mSupportFragment = fragment
        return this
    }

    /**
     * Specifies request code that used in activity result
     *
     * @see [Getting a Result from an Activity](https://developer.android.com/training/basics/intents/result.html)
     */
    fun withRequestCode(requestCode: Int): FilePicker {
        mRequestCode = requestCode
        return this
    }


    /**
     * Hides files that matched by specified regular expression.
     * Use [withFilterDirectories][FilePicker.withFilterDirectories] method
     * to enable directories filtering
     */
    fun withFilter(pattern: Pattern): FilePicker {
        mFileFilter = pattern
        return this
    }

    /**
     * If directoriesFilter is true directories will also be affected by filter,
     * the default value of directories filter is false
     *
     * @see FilePicker.withFilter
     */
    fun withFilterDirectories(directoriesFilter: Boolean): FilePicker {
        mDirectoriesFilter = directoriesFilter
        return this
    }

    /**
     * Specifies root directory for picker,
     * user can't go upper that specified path
     */
    fun withRootPath(rootPath: String): FilePicker {
        mRootPath = rootPath
        return this
    }

    /**
     * Specifies start directory for picker,
     * which will be shown to user at the beginning
     */
    fun withPath(path: String): FilePicker {
        mCurrentPath = path
        return this
    }

    /**
     * Show or hide hidden files in picker
     */
    fun withHiddenFiles(show: Boolean): FilePicker {
        mShowHidden = show
        return this
    }

    /**
     * Show or hide close menu in picker
     */
    fun withCloseMenu(closeable: Boolean): FilePicker {
        mCloseable = closeable
        return this
    }

    /**
     * Set title of picker
     */
    fun withTitle(title: CharSequence): FilePicker {
        mTitle = title
        return this
    }

    fun withCustomActivity(customActivityClass: Class<out FilePickerActivity>): FilePicker {
        mFilePickerClass = customActivityClass
        return this
    }

    /**
     * Open Material File Picker activity.
     * You should set Activity or Fragment before calling this method
     *
     * @see FilePicker.withActivity
     * @see FilePicker.withFragment
     * @see FilePicker.withSupportFragment
     */
    fun start() {
        if (mActivity == null && mFragment == null && mSupportFragment == null) {
            throw RuntimeException("You must pass Activity/Fragment by calling withActivity/withFragment/withSupportFragment method")
        }

        if (mRequestCode == null) {
            throw RuntimeException("You must pass request code by calling withRequestCode method")
        }

        val intent = intent

        if (mActivity != null) {
            mActivity!!.startActivityForResult(intent, mRequestCode!!)
        } else if (mFragment != null) {
            mFragment!!.startActivityForResult(intent, mRequestCode!!)
        } else {
            mSupportFragment!!.startActivityForResult(intent, mRequestCode!!)
        }
    }
}