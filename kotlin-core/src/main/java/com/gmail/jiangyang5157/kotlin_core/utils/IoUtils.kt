package com.gmail.jiangyang5157.kotlin_core.utils

import java.io.*

/**
 * Created by Yang Jiang on June 27, 2017
 */
object IoUtils {

    interface OnReadingListener {
        /**
         * Return false to stop
         */
        fun onReadLine(line: String?): Boolean
    }

    @Throws(java.io.IOException::class)
    fun read(inputStream: java.io.InputStream, onReadingListener: OnReadingListener) {
        val reader = java.io.BufferedReader(java.io.InputStreamReader(inputStream))
        var valid = true
        while (valid) {
            valid = onReadingListener.onReadLine(reader.readLine())
        }

        reader.close()
    }

    @Throws(java.io.IOException::class)
    fun read(inputStream: java.io.InputStream): String {
        val body = StringBuilder()
        com.gmail.jiangyang5157.kotlin_core.utils.IoUtils.read(inputStream, object : OnReadingListener {
            override fun onReadLine(line: String?): Boolean {
                if (line == null) {
                    return false
                } else {
                    body.append(line).append(System.getProperty("line.separator"))
                    return true
                }
            }
        })

        return body.toString()
    }

    @Throws(java.io.IOException::class)
    fun write(inputStream: java.io.InputStream, dst: java.io.File) {
        dst.parentFile.mkdirs()

        val BUFFER_SIZE = 1024
        val buffer = ByteArray(BUFFER_SIZE)
        val fileOutputStream = java.io.FileOutputStream(dst)
        val bufferedOutputStream = java.io.BufferedOutputStream(fileOutputStream, BUFFER_SIZE)

        do {
            val length = inputStream.read(buffer, 0, BUFFER_SIZE)
            if (length != -1) {
                bufferedOutputStream.write(buffer, 0, length)
            } else {
                break
            }
        } while (true)

        bufferedOutputStream.flush()
        bufferedOutputStream.close()
        fileOutputStream.flush()
        fileOutputStream.close()
    }

    @Throws(java.io.IOException::class)
    fun copy(src: java.io.File, dst: java.io.File) {
        com.gmail.jiangyang5157.kotlin_core.utils.IoUtils.write(java.io.FileInputStream(src), dst)
    }

    @Throws(java.io.IOException::class)
    fun unzip(inputStream: java.io.InputStream, dst: java.io.File, replace: Boolean) {
        val bufferedInputStream = java.io.BufferedInputStream(inputStream)
        val zipInputStream = java.util.zip.ZipInputStream(bufferedInputStream)

        do {
            val zipEntry = zipInputStream.nextEntry
            if (zipEntry != null) {
                val file = java.io.File(dst.absolutePath + File.separator + zipEntry.name)
                if (zipEntry.isDirectory) {
                    file.mkdirs()
                } else if (file.isDirectory || replace || !file.exists()) {
                    com.gmail.jiangyang5157.kotlin_core.utils.IoUtils.write(zipInputStream, file)
                }
            } else {
                break
            }
        } while (true)

        zipInputStream.close()
        bufferedInputStream.close()
    }

}
