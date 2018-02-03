package com.kelfan.kotlineditor.ui.filepicker

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
import android.widget.TextView
import com.kelfan.kotlineditor.R
import com.kelfan.kotlineditor.util.FileWorker
import java.io.File
import java.io.FileFilter
import java.lang.reflect.Field
import java.util.*
import java.util.regex.Pattern

/**
 * Created by Administrator on 3/02/2018.
 */
class FilePickerActivity : AppCompatActivity(), DirectoryFragment.FileClickListener {

    private var mToolbar: Toolbar? = null
    private var mStartPath: String? = Environment.getExternalStorageDirectory().absolutePath
    private var mCurrentPath = mStartPath
    private var mTitle: CharSequence? = null

    private var mCloseable: Boolean? = null

    private var mFilter: FilterComposite? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.file_picker_activity)

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
                filters.add(FilterPattern(filter, false))
                mFilter = FilterComposite(filters)
            } else {
                mFilter = filter as FilterComposite
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
        mToolbar = findViewById<Toolbar>(R.id.toolbar)
    }

    private fun initFragment() {
        fragmentManager.beginTransaction()
                .replace(R.id.container, DirectoryFragment.getInstance(
                        this!!.mCurrentPath!!, this!!.mFilter!!))
                .addToBackStack(null)
                .commit()
    }

    private fun initBackStackState() {
        var pathToAdd = mCurrentPath
        val separatedPaths = ArrayList<String>()

        while (pathToAdd != mStartPath) {
            pathToAdd = FileWorker.cutLastSegmentOfPath(pathToAdd!!)
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
                supportActionBar!!.setTitle(titlePath)
            } else {
                supportActionBar!!.setSubtitle(titlePath)
            }
        }
    }

    private fun addFragmentToBackStack(path: String) {
        fragmentManager.beginTransaction()
                .replace(R.id.container, DirectoryFragment.getInstance(
                        path, this!!.mFilter!!))
                .addToBackStack(null)
                .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.filepicker_menu, menu)
        menu.findItem(R.id.filepicker_action_close).isVisible = mCloseable!!
        return true
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        if (menuItem.itemId == android.R.id.home) {
            onBackPressed()
        } else if (menuItem.itemId == R.id.filepicker_action_close) {
            finish()
        }
        return super.onOptionsItemSelected(menuItem)
    }

    override fun onBackPressed() {
        val fm = fragmentManager

        if (mCurrentPath != mStartPath) {
            fm.popBackStack()
            mCurrentPath = FileWorker.cutLastSegmentOfPath(this!!.mCurrentPath!!)
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
            var mCurrentPath = clickedFile.path
            // If the user wanna go to the emulated directory, he will be taken to the
            // corresponding user emulated folder.
            if (mCurrentPath == "/storage/emulated")
                mCurrentPath = Environment.getExternalStorageDirectory().absolutePath
            addFragmentToBackStack(mCurrentPath)
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