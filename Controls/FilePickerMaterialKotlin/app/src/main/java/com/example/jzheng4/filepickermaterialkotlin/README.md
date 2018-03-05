# cite 
https://github.com/nbsp-team/MaterialFilePicker


# structure 
filter/HiddenFilter
filter/PatternFilter 
filter/CompositeFilter
utils/FileComparator 
utils/FileUtils
widget/EmptyRecyclerView
utils/FileTypeUtils <- drawable/icons 
utils/FileTypeUtils <- values/string
ui/DirectoryFragment <- FileTypeUtils
ui/DirectoryFragment <- DirectoryAdapter
ui/DirectoryFragment <- R.layout.fragment_directory
ui/DirectoryFragment <- R.id.directory_recycler_view
ui/DirectoryFragment <- R.id.directory_empty_view

# main chain 
MainActivity <- MaterialFilePicker <- other packages 
MainActivity <- SampleFragment <- {FilePickerActivity|R.layout.fragment_sample|}

# working


# package/ui
DirectoryAdapter -> DirectoryFragment -> FilePickerActivity

# res
item_file.xml -> DirectoryAdapter
menu.xml -> FilePickerActivity
activity_file_picker.xml -> FilePickerActivity
fragment_directory.xml -> DirectoryFragment
fragment_directory.xml <- EmptyRecyclerView
activity_main -> MainActivity 

# requirements 
- app or library dependency 
```xml
    compile 'com.android.support:appcompat-v7:25.3.1'
    compile 'com.android.support:recyclerview-v7:25.3.1'
    compile 'com.android.support:design:25.3.1'
```

# requirements/ declare activity 
```xml
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.nbsp.materialfilepicker">
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />

    <application>

        <activity android:name=".ui.FilePickerActivity"
            android:theme="@style/MFP_BaseTheme">
        </activity>
    </application>
</manifest>

```

# icons 
https://www.iconfinder.com/iconsets/hawcons