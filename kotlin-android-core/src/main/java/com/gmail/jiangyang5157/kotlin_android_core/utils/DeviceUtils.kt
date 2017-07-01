package com.gmail.jiangyang5157.kotlin_android_core.utils

import android.provider.MediaStore
import android.provider.DocumentsContract
import android.content.ContentUris
import android.os.Build
import android.os.StatFs
import android.app.ActivityManager
import android.content.Context
import android.content.res.Resources
import android.database.Cursor
import android.net.ParseException
import android.net.Uri
import android.os.Environment.*
import android.util.TypedValue.*
import java.io.File
import java.io.IOException
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Yang Jiang on July 01, 2017
 */
object DeviceUtils {

    val STORAGE_UNIT = 1024
    val SYMBOL_BYTE = "BYTE"
    val SYMBOL_KB = "KB"
    val SYMBOL_MB = "MB"
    val SYMBOL_GB = "GB"

    fun sdkValidate(sdkVersion: Int) = Build.VERSION.SDK_INT >= sdkVersion

    fun glesValidate(context: Context, version: Int) = (context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager).deviceConfigurationInfo.reqGlEsVersion >= version

    /**
     * Return integer pixels. An offset conversion involves simply truncating the base value to an integer.
     */
    fun getDimensionPixelOffset(resources: Resources, resId: Int) = resources.getDimensionPixelOffset(resId)

    fun dpToPx(dp: Float, resources: Resources) = applyDimension(COMPLEX_UNIT_DIP, dp, resources.displayMetrics).toInt()

    /**
     * Return true if external storage is available for read and write
     */
    val isExternalStorageWritable: Boolean
        get() = MEDIA_MOUNTED == getExternalStorageState()

    /**
     *  Return true if external storage is available to at least read
     */
    val isExternalStorageReadable: Boolean
        get() {
            val state = getExternalStorageState()
            return MEDIA_MOUNTED == state || MEDIA_MOUNTED_READ_ONLY == state
        }

    val sdacrdFile: File
        get() = getExternalStorageDirectory()

    @Throws(IOException::class)
    fun authority(filePath: String) {
        Runtime.getRuntime().exec("chmod 777 " + filePath)
    }

    private fun getSize(fileLength: Long, symbol: String): Double {
        var ret = 0.0
        when (symbol) {
            SYMBOL_BYTE -> ret = fileLength.toFloat().toDouble()
            SYMBOL_KB -> ret = (fileLength.toFloat() / STORAGE_UNIT).toDouble()
            SYMBOL_MB -> ret = (fileLength.toFloat() / (STORAGE_UNIT * STORAGE_UNIT)).toDouble()
            SYMBOL_GB -> ret = (fileLength.toFloat() / (STORAGE_UNIT * STORAGE_UNIT * STORAGE_UNIT)).toDouble()
        }

        val scale = 2
        val roundingMode = BigDecimal.ROUND_UP
        var bigDecimal = BigDecimal(ret)
        bigDecimal = bigDecimal.setScale(scale, roundingMode)
        ret = bigDecimal.toDouble()
        return ret
    }

    @Deprecated("")
    fun getAvailableSize(file: File, symbol: String): Double {
        val statFs = StatFs(file.path)
        val blockSize = statFs.blockSizeLong
        val availableBlocks = statFs.availableBlocksLong
        val available = availableBlocks * blockSize
        return getSize(available, symbol)
    }

    fun getSize(file: File, symbol: String) = getSize(file.length(), symbol)

    /**
     * @return true if the Uri authority is ExternalStorageProvider.
     */
    private fun isExternalStorageDocument(uri: Uri) = "com.android.externalstorage.documents" == uri.authority

    /**
     * @return true if the Uri authority is DownloadsProvider.
     */
    private fun isDownloadsDocument(uri: Uri) = "com.android.providers.downloads.documents" == uri.authority

    /**
     * @return true if the Uri authority is MediaProvider.
     */
    private fun isMediaDocument(uri: Uri) = "com.android.providers.media.documents" == uri.authority

    /**
     * @return true if the Uri authority is Google Photos.
     */
    private fun isGooglePhotosUri(uri: Uri) = "com.google.android.apps.photos.content" == uri.authority

    /**
     * Since path might be "/storage/emulated/0/..." or "content://com.android.externalstorage.documents/document/primary:..." etc.
     * Get a file path from a Uri. This will get the the path for Storage Access
     * Framework Documents, as well as the _data field for the MediaStore and
     * other file-based ContentProviders.

     * @param context The context.
     * *
     * @param uri     The uri to query
     */
    fun getPath(context: Context, uri: Uri): String? {
        // DocumentProvider
        if (sdkValidate(Build.VERSION_CODES.KITKAT) && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                val docId = DocumentsContract.getDocumentId(uri)
                val split = docId.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                val type = split[0]
                if ("primary".equals(type, ignoreCase = true)) {
                    return getExternalStorageDirectory().absolutePath + "/" + split[1]
                }
            } else if (isDownloadsDocument(uri)) {
                // DownloadsProvider
                val id = DocumentsContract.getDocumentId(uri)
                val contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), java.lang.Long.valueOf(id)!!)
                return getDataColumn(context, contentUri, null, null)
            } else if (isMediaDocument(uri)) {
                // MediaProvider
                val docId = DocumentsContract.getDocumentId(uri)
                val split = docId.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                val type = split[0]
                var contentUri: Uri? = null
                if ("image" == type) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                } else if ("video" == type) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                } else if ("audio" == type) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                }

                val selection = "_id=?"
                val selectionArgs = arrayOf(split[1])
                return getDataColumn(context, contentUri!!, selection, selectionArgs)
            }
        } else if ("content".equals(uri.scheme, ignoreCase = true)) {
            // MediaStore (and general)
            // Return the remote address
            if (isGooglePhotosUri(uri)) {
                return uri.lastPathSegment
            }
            return getDataColumn(context, uri, null, null)
        } else if ("file".equals(uri.scheme, ignoreCase = true)) {
            // File
            return uri.path
        }
        return null
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.

     * @param context       The context.
     * *
     * @param uri           The Uri to query.
     * *
     * @param selection     (Optional) Filter used in the query.
     * *
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * *
     * @return The value of the _data column, which is typically a file path.
     */
    fun getDataColumn(context: Context, uri: Uri, selection: String?, selectionArgs: Array<String>?): String? {
        var ret: String? = null

        val column = "_data"
        val projection = arrayOf(column)

        var cursor: Cursor? = null
        try {
            cursor = context.contentResolver.query(uri, projection, selection, selectionArgs, null)
            if (cursor != null && cursor.moveToFirst()) {
                ret = cursor.getString(cursor.getColumnIndexOrThrow(column))
            }
        } finally {
            if (cursor != null) {
                cursor.close()
            }
        }
        return ret
    }

    fun buildStringDate(template: String) = buildDate(template).format(Date())

    fun buildStringDate(milliseconds: Long, template: String) = buildDate(template).format(Date(milliseconds))

    fun buildLongDate() = Date().time

    @Throws(ParseException::class)
    fun buildLongDate(stringDate: String, template: String) = buildDate(template).parse(stringDate).time

    private fun buildDate(template: String, locale: Locale = Locale.getDefault()) = SimpleDateFormat(template, locale)
}
