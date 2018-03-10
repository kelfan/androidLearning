package com.kelfan.filepicker.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import com.kelfan.filepicker.filter.CompositeFilter
import com.kelfan.filepicker.filter.PatternFilter
import com.kelfan.filepicker.utils.FileUtils
import com.kelfan.filepickermaterialcombinejava.R
import java.io.File
import java.io.FileFilter
import java.lang.reflect.Field
import java.util.*
import java.util.regex.Pattern

/**
 * Created by Administrator on 7/03/2018.
 */
class FilePickerActivity : AppCompatActivity(), DirectoryFragment.FileClickListener {

    private var mToolbar: Toolbar? = null
    private var mStartPath: String? = Environment.getExternalStorageDirectory().absolutePath
    private var mCurrentPath = mStartPath
    private var mTitle: CharSequence? = null

    private var mCloseable: Boolean? = null

    private var mFilter: CompositeFilter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_picker)

        initArguments(savedInstanceState)
        initViews()
        initToolbar()
        initBackStackState()
        initFragment()
    }

    private fun initArguments(savedInstanceState: Bundle?) {
        if (intent.hasExtra(ARG_FILTER)) {
            val filter = intent.getSerializableExtra(ARG_FILTER)

            if (filter is Pattern) {
                val filters = ArrayList<FileFilter>()
                filters.add(PatternFilter(filter, false))
                mFilter = CompositeFilter(filters)
            } else {
                mFilter = filter as CompositeFilter
            }
        }

        if (savedInstanceState != null) {
            mStartPath = savedInstanceState.getString(STATE_START_PATH)
            mCurrentPath = savedInstanceState.getString(STATE_CURRENT_PATH)
            updateTitle()
        } else {
            if (intent.hasExtra(ARG_START_PATH)) {
                mStartPath = intent.getStringExtra(ARG_START_PATH)
                mCurrentPath = mStartPath
            }

            if (intent.hasExtra(ARG_CURRENT_PATH)) {
                val currentPath = intent.getStringExtra(ARG_CURRENT_PATH)

                if (currentPath.startsWith(mStartPath!!)) {
                    mCurrentPath = currentPath
                }
            }
        }

        if (intent.hasExtra(ARG_TITLE)) {
            mTitle = intent.getCharSequenceExtra(ARG_TITLE)
        }

        if (intent.hasExtra(ARG_CLOSEABLE)) {
            mCloseable = intent.getBooleanExtra(ARG_CLOSEABLE, true)
        }
    }

    private fun initToolbar() {
        setSupportActionBar(mToolbar)

        // Show back button
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }

        // Truncate start of path
        try {
            val f: Field
            if (TextUtils.isEmpty(mTitle)) {
                f = mToolbar!!.javaClass.getDeclaredField("mTitleTextView")
            } else {
                f = mToolbar!!.javaClass.getDeclaredField("mSubtitleTextView")
            }

            f.isAccessible = true
            val textView = f.get(mToolbar) as TextView
            textView.ellipsize = TextUtils.TruncateAt.START
        } catch (ignored: Exception) {
        }

        if (!TextUtils.isEmpty(mTitle)) {
            title = mTitle
        }
        updateTitle()
    }

    private fun initViews() {
        mToolbar = findViewById<View>(R.id.toolbar) as Toolbar?
    }

    private fun initFragment() {
        val instance = DirectoryFragment.getInstance(mCurrentPath!!, mFilter!!)
        fragmentManager.beginTransaction()
                .replace(R.id.container, instance)
                .addToBackStack(null)
                .commit()
    }

    private fun initBackStackState() {
        var pathToAdd = mCurrentPath
        val separatedPaths = ArrayList<String>()

        while (pathToAdd != mStartPath) {
            pathToAdd = FileUtils.cutLastSegmentOfPath(pathToAdd!!)
            separatedPaths.add(pathToAdd)
        }

        Collections.reverse(separatedPaths)

        for (path in separatedPaths) {
            addFragmentToBackStack(path)
        }
    }

    private fun updateTitle() {
        if (supportActionBar != null) {
            val titlePath = if (mCurrentPath!!.isEmpty()) "/" else mCurrentPath
            if (TextUtils.isEmpty(mTitle)) {
                supportActionBar!!.title = titlePath
            } else {
                supportActionBar!!.subtitle = titlePath
            }
        }
    }

    private fun addFragmentToBackStack(path: String) {
        val instance = DirectoryFragment.getInstance(path, mFilter!!)
        fragmentManager.beginTransaction()
                .replace(R.id.container, instance)
                .addToBackStack(null)
                .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        menu.findItem(R.id.action_close).isVisible = mCloseable!!
        return true
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        if (menuItem.itemId == android.R.id.home) {
            onBackPressed()
        } else if (menuItem.itemId == R.id.action_close) {
            finish()
        }
        return super.onOptionsItemSelected(menuItem)
    }

    override fun onBackPressed() {
        val fm = fragmentManager

        if (mCurrentPath != mStartPath) {
            fm.popBackStack()
            mCurrentPath = FileUtils.cutLastSegmentOfPath(mCurrentPath!!)
            updateTitle()
        } else {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }

    public override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState!!.putString(STATE_CURRENT_PATH, mCurrentPath)
        outState.putString(STATE_START_PATH, mStartPath)
    }

    override fun onFileClicked(clickedFile: File) {
        Handler().postDelayed({ handleFileClicked(clickedFile) }, HANDLE_CLICK_DELAY.toLong())
    }

    private fun handleFileClicked(clickedFile: File) {
        if (clickedFile.isDirectory) {
            mCurrentPath = clickedFile.path
            // If the user wanna go to the emulated directory, he will be taken to the
            // corresponding user emulated folder.
            if (mCurrentPath == "/storage/emulated")
                mCurrentPath = Environment.getExternalStorageDirectory().absolutePath
            addFragmentToBackStack(mCurrentPath!!)
            updateTitle()
        } else {
            setResultAndFinish(clickedFile.path)
        }
    }

    private fun setResultAndFinish(filePath: String) {
        val data = Intent()
        data.putExtra(RESULT_FILE_PATH, filePath)
        setResult(Activity.RESULT_OK, data)
        finish()
    }

    companion object {
        val ARG_START_PATH = "arg_start_path"
        val ARG_CURRENT_PATH = "arg_current_path"

        val ARG_FILTER = "arg_filter"
        val ARG_CLOSEABLE = "arg_closeable"
        val ARG_TITLE = "arg_title"

        val STATE_START_PATH = "state_start_path"
        private val STATE_CURRENT_PATH = "state_current_path"

        val RESULT_FILE_PATH = "result_file_path"
        private val HANDLE_CLICK_DELAY = 150
    }
}