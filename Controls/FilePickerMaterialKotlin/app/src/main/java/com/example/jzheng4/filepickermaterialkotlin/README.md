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


# working
fragment_directory.xml

# package/ui
DirectoryAdapter -> DirectoryFragment -> FilePickerActivity


# res
fragment_directory.xml -> DirectoryAdapter

# requirements 
- app or library dependency 
```xml
    compile 'com.android.support:appcompat-v7:25.3.1'
    compile 'com.android.support:recyclerview-v7:25.3.1'
    compile 'com.android.support:design:25.3.1'
```

# icons 
https://www.iconfinder.com/iconsets/hawcons