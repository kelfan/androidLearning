package com.kelfan.filepickermaterialcombinejava

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.kelfan.filepicker.MaterialFilePicker
import com.kelfan.filepicker.ui.FilePickerActivity

/**
 * Created by Administrator on 8/03/2018.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pickButton = findViewById<View>(R.id.pick_from_activity) as Button
        pickButton.setOnClickListener { checkPermissionsAndOpenFilePicker() }
    }

    private fun checkPermissionsAndOpenFilePicker() {
        val permission = Manifest.permission.READ_EXTERNAL_STORAGE

        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                showError()
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(permission), PERMISSIONS_REQUEST_CODE)
            }
        } else {
            openFilePicker()
        }
    }

    private fun showError() {
        Toast.makeText(this, "Allow external storage reading", Toast.LENGTH_SHORT).show()
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSIONS_REQUEST_CODE -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openFilePicker()
                } else {
                    showError()
                }
            }
        }
    }

    private fun openFilePicker() {
        MaterialFilePicker()
                .withActivity(this)
                .withRequestCode(FILE_PICKER_REQUEST_CODE)
                .withHiddenFiles(true)
                .withTitle("Sample title")
                .start()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == FILE_PICKER_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val path = data?.getStringExtra(FilePickerActivity.RESULT_FILE_PATH)

            if (path != null) {
                Log.d("Path: ", path)
                Toast.makeText(this, "Picked file: $path", Toast.LENGTH_LONG).show()
            }
        }
    }

    companion object {

        val PERMISSIONS_REQUEST_CODE = 0
        val FILE_PICKER_REQUEST_CODE = 1
    }
}