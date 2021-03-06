package com.kelfan.filepicker;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;


import java.io.FileFilter;
import java.util.ArrayList;
import java.util.regex.Pattern;


/**
 * Material File Picker builder
 */
public class MaterialFilePicker {
    private Activity mActivity;
    private Fragment mFragment;
    private android.support.v4.app.Fragment mSupportFragment;

    private Class<? extends ActivityFilePicker> mFilePickerClass = ActivityFilePicker.class;

    private Integer mRequestCode;
    private Pattern mFileFilter;
    private Boolean mDirectoriesFilter = false;
    private String mRootPath;
    private String mCurrentPath;
    private Boolean mShowHidden = false;
    private Boolean mCloseable = true;
    private CharSequence mTitle;

    public MaterialFilePicker() {
    }


    /**
     * Specifies activity, which will be used to
     * start file picker
     */
    public MaterialFilePicker withActivity(Activity activity) {
        if (mSupportFragment != null || mFragment != null) {
            throw new RuntimeException("You must pass either Activity, Fragment or SupportFragment");
        }

        mActivity = activity;
        return this;
    }

    /**
     * Specifies fragment, which will be used to
     * start file picker
     */
    public MaterialFilePicker withFragment(Fragment fragment) {
        if (mSupportFragment != null || mActivity != null) {
            throw new RuntimeException("You must pass either Activity, Fragment or SupportFragment");
        }

        mFragment = fragment;
        return this;
    }

    /**
     * Specifies support fragment which will be used to
     * start file picker
     */
    public MaterialFilePicker withSupportFragment(android.support.v4.app.Fragment fragment) {
        if (mActivity != null || mFragment != null) {
            throw new RuntimeException("You must pass either Activity, Fragment or SupportFragment");
        }

        mSupportFragment = fragment;
        return this;
    }

    /**
     * Specifies request code that used in activity result
     *
     * @see <a href="https://developer.android.com/training/basics/intents/result.html">Getting a Result from an Activity</a>
     */
    public MaterialFilePicker withRequestCode(int requestCode) {
        mRequestCode = requestCode;
        return this;
    }


    /**
     * Hides files that matched by specified regular expression.
     * Use {@link MaterialFilePicker#withFilterDirectories withFilterDirectories} method
     * to enable directories filtering
     */
    public MaterialFilePicker withFilter(Pattern pattern) {
        mFileFilter = pattern;
        return this;
    }

    /**
     * If directoriesFilter is true directories will also be affected by filter,
     * the default value of directories filter is false
     *
     * @see MaterialFilePicker#withFilter
     */
    public MaterialFilePicker withFilterDirectories(boolean directoriesFilter) {
        mDirectoriesFilter = directoriesFilter;
        return this;
    }

    /**
     * Specifies root directory for picker,
     * user can't go upper that specified path
     */
    public MaterialFilePicker withRootPath(String rootPath) {
        mRootPath = rootPath;
        return this;
    }

    /**
     * Specifies start directory for picker,
     * which will be shown to user at the beginning
     */
    public MaterialFilePicker withPath(String path) {
        mCurrentPath = path;
        return this;
    }

    /**
     * Show or hide hidden files in picker
     */
    public MaterialFilePicker withHiddenFiles(boolean show) {
        mShowHidden = show;
        return this;
    }

    /**
     * Show or hide close filepicker_menu in picker
     */
    public MaterialFilePicker withCloseMenu(boolean closeable) {
        mCloseable = closeable;
        return this;
    }

    /**
     * Set title of picker
     */
    public MaterialFilePicker withTitle(CharSequence title) {
        mTitle = title;
        return this;
    }

    public MaterialFilePicker withCustomActivity(Class<? extends ActivityFilePicker> customActivityClass) {
        mFilePickerClass = customActivityClass;
        return this;
    }

    public FilterComposite getFilter() {
        ArrayList<FileFilter> filters = new ArrayList<>();

        if (!mShowHidden) {
            filters.add(new FilterHidden());
        }

        if (mFileFilter != null) {
            filters.add(new FilterPattern(mFileFilter, mDirectoriesFilter));
        }

        return new FilterComposite(filters);
    }


    /**
     * @return Intent that can be used to start Material File Picker
     */
    public Intent getIntent() {
        FilterComposite filter = getFilter();

        Activity activity = null;
        if (mActivity != null) {
            activity = mActivity;
        } else if (mFragment != null) {
            activity = mFragment.getActivity();
        } else if (mSupportFragment != null) {
            activity = mSupportFragment.getActivity();
        }

        Intent intent = new Intent(activity, mFilePickerClass);
        intent.putExtra(ActivityFilePicker.ARG_FILTER, filter);
        intent.putExtra(ActivityFilePicker.ARG_CLOSEABLE, mCloseable);

        if (mRootPath != null) {
            intent.putExtra(ActivityFilePicker.ARG_START_PATH, mRootPath);
        }

        if (mCurrentPath != null) {
            intent.putExtra(ActivityFilePicker.ARG_CURRENT_PATH, mCurrentPath);
        }

        if (mTitle != null) {
            intent.putExtra(ActivityFilePicker.ARG_TITLE, mTitle);
        }

        return intent;
    }

    /**
     * Open Material File Picker activity.
     * You should set Activity or Fragment before calling this method
     *
     * @see MaterialFilePicker#withActivity(Activity)
     * @see MaterialFilePicker#withFragment(Fragment)
     * @see MaterialFilePicker#withSupportFragment(android.support.v4.app.Fragment)
     */
    public void start() {
        if (mActivity == null && mFragment == null && mSupportFragment == null) {
            throw new RuntimeException("You must pass Activity/Fragment by calling withActivity/withFragment/withSupportFragment method");
        }

        if (mRequestCode == null) {
            throw new RuntimeException("You must pass request code by calling withRequestCode method");
        }

        Intent intent = getIntent();

        if (mActivity != null) {
            mActivity.startActivityForResult(intent, mRequestCode);
        } else if (mFragment != null) {
            mFragment.startActivityForResult(intent, mRequestCode);
        } else {
            mSupportFragment.startActivityForResult(intent, mRequestCode);
        }
    }
}
