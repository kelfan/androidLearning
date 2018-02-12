# Material File Picker 
source: 
- https://www.youtube.com/watch?v=LrQtu0Juu5I
- https://github.com/nbsp-team/MaterialFilePicker

process:
- build.gradle(Project:)
```yaml
allprojects {
    repositories {
        google()
        jcenter()
        maven {
            url  "http://dl.bintray.com/lukaville/maven"
        }
    }
}
```
- build.gradle(Module:app)
```yaml
dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation"org.jetbrains.kotlin:kotlin-stdlib-jre7:$kotlin_version"
    implementation 'com.android.support:appcompat-v7:26.1.0'
    implementation 'com.android.support:design:26.1.0'
    implementation 'com.android.support.constraint:constraint-layout:1.0.2'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'com.android.support.test:runner:1.0.1'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.1'
    compile 'com.nbsp:library:1.8'
}
```
- new MaterialFilePicker within action trigger 
```java
new MaterialFilePicker()
    .withActivity(this)
    .withRequestCode(1)
    .withFilter(Pattern.compile(".*\\.txt$")) // Filtering files and directories by file name using regexp
    .withFilterDirectories(true) // Set directories filterable (false by default)
    .withHiddenFiles(true) // Show hidden files and folders
    .start();
```
- override activity result back 
```java
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (requestCode == 1 && resultCode == RESULT_OK) {
        String filePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
        // Do anything with file
    }
}
```
- add use-permission in manifests
```xml
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
```
