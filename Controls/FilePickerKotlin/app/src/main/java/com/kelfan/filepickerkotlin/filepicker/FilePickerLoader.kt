package com.kelfan.filepickerkotlin.filepicker

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.Toast
import com.kelfan.filepickerkotlin.utils.Constant
import com.nbsp.materialfilepicker.MaterialFilePicker

/**
 * Created by Administrator on 12/02/2018.
 */
class FilePickerLoader {

    companion object {
        fun checkPermissionsAndOpenFilePicker(activity: Activity) {
            val permission = Manifest.permission.READ_EXTERNAL_STORAGE

            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                    showError(activity)
                } else {
                    ActivityCompat.requestPermissions(activity, arrayOf(permission), Constant.PERMISSIONS_REQUEST_CODE)
                }
            } else {
                openFilePicker(activity)
            }
        }

        private fun openFilePicker(activity: Activity) {
            MaterialFilePicker()
                    .withActivity(activity)
                    .withRequestCode(Constant.FILE_PICKER_REQUEST_CODE)
                    .withHiddenFiles(false)
                    .withTitle("File Browser")
                    .start()
        }

        private fun showError(activity: Activity) {
            Toast.makeText(activity, "Allow external storage reading", Toast.LENGTH_SHORT).show()
        }
    }




}