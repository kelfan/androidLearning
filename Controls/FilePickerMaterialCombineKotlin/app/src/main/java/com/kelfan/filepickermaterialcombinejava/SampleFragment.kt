package com.kelfan.filepickermaterialcombinejava

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.kelfan.filepicker.MaterialFilePicker
import com.kelfan.filepicker.ui.FilePickerActivity

/**
 * Created by Administrator on 8/03/2018.
 */


class SampleFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_sample, container, false)

        val pickButton = view.findViewById<View>(R.id.pick_from_fragment) as Button
        pickButton.setOnClickListener { checkPermissionsAndOpenFilePicker() }

        return view
    }

    private fun checkPermissionsAndOpenFilePicker() {
        val permission = Manifest.permission.READ_EXTERNAL_STORAGE

        if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                showError()
            } else {
                ActivityCompat.requestPermissions(activity, arrayOf(permission), PERMISSIONS_REQUEST_CODE)
            }
        } else {
            openFilePicker()
        }
    }

    private fun showError() {
        Toast.makeText(context, "Allow external storage reading", Toast.LENGTH_SHORT).show()
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
                .withSupportFragment(this)
                .withRequestCode(FILE_PICKER_REQUEST_CODE)
                .withHiddenFiles(true)
                .start()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == FILE_PICKER_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val path = data!!.getStringExtra(FilePickerActivity.RESULT_FILE_PATH)

            if (path != null) {
                Log.d("Path (fragment): ", path)
                Toast.makeText(context, "Picked file in fragment: $path", Toast.LENGTH_LONG).show()
            }
        }
    }

    companion object {

        val PERMISSIONS_REQUEST_CODE = 0
        val FILE_PICKER_REQUEST_CODE = 1
    }
}