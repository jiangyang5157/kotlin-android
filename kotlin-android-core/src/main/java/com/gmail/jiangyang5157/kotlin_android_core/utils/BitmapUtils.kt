package com.gmail.jiangyang5157.kotlin_android_core.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory.*
import android.graphics.Matrix
import java.io.*
import java.net.URL

/**
 * Created by Yang Jiang on July 01, 2017
 */
object BitmapUtils {

    fun load(context: Context, resId: Int): Bitmap = decodeResource(context.resources, resId)

    /**
     * @param sampleSize dst.width = src.width/sampleSize; dst.high = src.high/sampleSize
     */
    @Throws(IOException::class)
    fun load(filePath: String, sampleSize: Int): Bitmap {
        val options = Options()
        options.inSampleSize = sampleSize

        val url = URL("file://" + filePath)
        val conn = url.openConnection()
        conn.doInput = true
        conn.connect()

        val bis = BufferedInputStream(conn.getInputStream())
        val ret = decodeStream(bis, null, options)

        bis.close()
        return ret
    }

    /**
     * @param quality [0, 100]
     */
    @Throws(IOException::class)
    fun save(src: Bitmap, file: File, format: Bitmap.CompressFormat, quality: Int) {
        val bos = BufferedOutputStream(FileOutputStream(file))
        src.compress(format, quality, bos)
        bos.flush()
        bos.close()
    }

    fun verticalReverse(src: Bitmap): Bitmap {
        return Bitmap.createScaledBitmap(src, src.width, -src.height, true)
    }

    fun horizontalReverse(src: Bitmap): Bitmap {
        return Bitmap.createScaledBitmap(src, -src.width, src.height, true)
    }

    fun diagonalReverse(src: Bitmap): Bitmap {
        return Bitmap.createScaledBitmap(src, -src.width, -src.height, true)
    }

    fun rotate(src: Bitmap, degrees: Float): Bitmap {
        val imgWidth = src.width
        val imgHeight = src.height
        val matrix = Matrix()
        matrix.postScale(imgWidth.toFloat(), imgHeight.toFloat())
        matrix.setRotate(degrees)
        return Bitmap.createBitmap(src, 0, 0, imgWidth, imgHeight, matrix, true)
    }

    fun scale(src: Bitmap, newWidth: Int, newHeight: Int): Bitmap {
        val oldWidth = src.width
        val oldHeight = src.height
        val scaleWidth = (newWidth / oldWidth).toFloat()
        val scaleHeight = (newHeight / oldHeight).toFloat()
        val matrix = Matrix()
        matrix.postScale(scaleWidth, scaleHeight)
        return Bitmap.createBitmap(src, 0, 0, oldWidth, oldHeight, matrix, true)
    }

    fun scale(src: Bitmap, scale: Float): Bitmap {
        val oldWidth = src.width
        val oldHeight = src.height
        val matrix = Matrix()
        matrix.postScale(scale, scale)
        return Bitmap.createBitmap(src, 0, 0, oldWidth, oldHeight, matrix, true)
    }

}