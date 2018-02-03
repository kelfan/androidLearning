package com.kelfan.kotlineditor.ui.filepicker

import android.webkit.MimeTypeMap
import com.kelfan.kotlineditor.R
import java.io.File
import java.io.UnsupportedEncodingException
import java.net.URLEncoder
import java.util.HashMap

/**
 * Created by Administrator on 2/02/2018.
 */
object FileTypeUtils {

    private val fileTypeExtensions = HashMap<String, FileType>()

    enum class FileType private constructor(val icon: Int, val description: Int, vararg extensions: String) {
        DIRECTORY(R.drawable.ic_folder_black_24dp, R.string.filetype_directory),
        DOCUMENT(R.drawable.ic_insert_drive_file_black_24dp, R.string.filetype_file),
        CERTIFICATE(R.drawable.ic_certificate_black_24dp, R.string.filetype_certificate, "cer", "der", "pfx", "p12", "arm", "pem"),
        DRAWING(R.drawable.ic_image_black_24dp, R.string.filetype_picture, "ai", "cdr", "dfx", "eps", "svg", "stl", "wmf", "emf", "art", "xar"),
        EXCEL(R.drawable.ic_excel_black_24dp, R.string.filetype_spreadsheet, "xls", "xlk", "xlsb", "xlsm", "xlsx", "xlr", "xltm", "xlw", "numbers", "ods", "ots"),
        IMAGE(R.drawable.ic_image_black_24dp, R.string.filetype_image, "bmp", "gif", "ico", "jpeg", "jpg", "pcx", "png", "psd", "tga", "tiff", "tif", "xcf"),
        MUSIC(R.drawable.ic_music_video_black_24dp, R.string.filetype_music, "aiff", "aif", "wav", "flac", "m4a", "wma", "amr", "mp2", "mp3", "wma", "aac", "mid", "m3u"),
        VIDEO(R.drawable.ic_video_label_black_24dp, R.string.filetype_video, "avi", "mov", "wmv", "mkv", "3gp", "f4v", "flv", "mp4", "mpeg", "webm"),
        PDF(R.drawable.ic_picture_as_pdf_black_24dp, R.string.filetype_pdf, "pdf"),
        POWER_POINT(R.drawable.ic_present_to_all_black_24dp, R.string.filetype_powerpoint, "pptx", "keynote", "ppt", "pps", "pot", "odp", "otp"),
        WORD(R.drawable.ic_word_black_24dp, R.string.filetype_word, "doc", "docm", "docx", "dot", "mcw", "rtf", "pages", "odt", "ott"),
        ARCHIVE(R.drawable.ic_archive_black_24dp, R.string.filetype_zip, "cab", "7z", "alz", "arj", "bzip2", "bz2", "dmg", "gzip", "gz", "jar", "lz", "lzip", "lzma", "zip", "rar", "tar", "tgz"),
        APK(R.drawable.ic_apps_black_24dp, R.string.filetype_apk, "apk");

        val extensions: Array<String>

        init {
            this.extensions = extensions as Array<String> // could be problem
        }
    }

    init {
        for (fileType in FileType.values()) {
            for (extension in fileType.extensions) {
                fileTypeExtensions[extension] = fileType
            }
        }
    }

    fun getFileType(file: File): FileType {
        if (file.isDirectory) {
            return FileType.DIRECTORY
        }

        val fileType = fileTypeExtensions[getExtension(file.name)]
        return fileType ?: FileType.DOCUMENT

    }

    fun getExtension(fileName: String): String {
        var encoded: String
        try {
            encoded = URLEncoder.encode(fileName, "UTF-8").replace("+", "%20")
        } catch (e: UnsupportedEncodingException) {
            encoded = fileName
        }

        return MimeTypeMap.getFileExtensionFromUrl(encoded).toLowerCase()
    }
}
