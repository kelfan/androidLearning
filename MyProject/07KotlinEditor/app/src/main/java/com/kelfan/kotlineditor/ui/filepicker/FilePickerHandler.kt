package com.kelfan.kotlineditor.ui.filepicker

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.Toast
import com.kelfan.kotlineditor.util.Constant
import com.kelfan.kotlineditor.util.Constant.Companion.PERMISSIONS_REQUEST_CODE

/**
 * Created by Administrator on 3/02/2018.
 */
class FilePickerHandler {
    companion object{
        public fun checkPermissionsAndOpenFilePicker(activity: Activity) {
            val permission = Manifest.permission.READ_EXTERNAL_STORAGE

            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                    showError(activity)
                } else {
                    ActivityCompat.requestPermissions(activity, arrayOf<String>(permission), PERMISSIONS_REQUEST_CODE)
                }
            } else {
                openFilePicker(activity)
            }
        }

        private fun showError(context: Context) {
            Toast.makeText(context, "Allow external storage reading", Toast.LENGTH_SHORT).show()
        }

        private fun openFilePicker(activity: Activity) {
            FilePicker()
                    .withActivity(activity)
                    .withRequestCode(Constant.FILE_PICKER_REQUEST_CODE)
                    .withHiddenFiles(true)
                    .withTitle("Sample title")
                    .start()
        }
    }
}