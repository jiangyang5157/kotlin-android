package com.gmail.jiangyang5157.kotlin.core.utils

// TODO fix; refactor; tests;
//object IoUtils {
//    private val TAG = "[IoUtils]"
//
//    interface OnReadingListener {
//        fun onReadLine(line: String): Boolean
//    }
//
//    @Throws(IOException::class)
//    fun read(`in`: InputStream): String {
//        val body = StringBuilder()
//        read(`in`, object : OnReadingListener {
//            override fun onReadLine(line: String?): Boolean {
//                if (line == null) {
//                    return false
//                } else {
//                    body.append(line).append(System.getProperty("line.separator"))
//                    return true
//                }
//            }
//        })
//
//        return body.toString()
//    }
//
//    @Throws(IOException::class)
//    fun read(`in`: InputStream, onReadingListener: OnReadingListener) {
//        val reader = BufferedReader(InputStreamReader(`in`))
//        var valid = true
//        while (valid) {
//            valid = onReadingListener.onReadLine(reader.readLine())
//        }
//        reader.close()
//    }
//
//    @Throws(IOException::class)
//    fun write(`in`: InputStream, dst: File) {
//        println(TAG + " write: " + dst.absolutePath)
//        dst.parentFile.mkdirs()
//
//        val BUFFER_SIZE = 1024
//        val buffer = ByteArray(BUFFER_SIZE)
//        val fileOutputStream = FileOutputStream(dst)
//        val bufferedOutputStream = BufferedOutputStream(fileOutputStream, BUFFER_SIZE)
//
//        var length: Int
//        while ((length = `in`.read(buffer, 0, BUFFER_SIZE)) != -1) {
//            bufferedOutputStream.write(buffer, 0, length)
//        }
//
//        bufferedOutputStream.flush()
//        bufferedOutputStream.close()
//        fileOutputStream.flush()
//        fileOutputStream.close()
//    }
//
//    @Throws(IOException::class)
//    fun copy(src: File, dst: File) {
//        println(TAG + " copy " + src.absolutePath + " to " + dst.absolutePath)
//        write(FileInputStream(src), dst)
//    }
//
//    @Throws(IOException::class)
//    fun unzip(`in`: InputStream, dst: File, replace: Boolean) {
//        val bufferedInputStream = BufferedInputStream(`in`)
//        val zipInputStream = ZipInputStream(bufferedInputStream)
//
//        var zipEntry: ZipEntry
//        while ((zipEntry = zipInputStream.nextEntry) != null) {
//            val zipEntryName = zipEntry.name
//            val file = File(dst.absolutePath + File.separator + zipEntryName)
//            println(TAG + " unzip: " + file.absolutePath)
//
//            if (zipEntry.isDirectory) {
//                file.mkdirs()
//            } else if (!file.exists() || file.isDirectory || replace) {
//                write(zipInputStream, file)
//            }
//        }
//
//        zipInputStream.close()
//        bufferedInputStream.close()
//    }
//
//}
