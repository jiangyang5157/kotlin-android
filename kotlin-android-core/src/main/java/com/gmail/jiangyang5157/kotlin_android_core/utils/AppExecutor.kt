package com.gmail.jiangyang5157.kotlin_android_core.utils

import android.os.Handler
import android.os.Looper

import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * Created by Yang Jiang on April 18, 2018
 *
 * Global executor pools for the whole application.
 * Grouping tasks like this avoids the effects of task starvation (e.g. disk reads don't wait behind webservice requests).
 */
open class AppExecutor constructor(
        val mainIo: Executor = MainThreadExecutor(),
        val diskIo: Executor = DiskIoThreadExecutor(),
        val networkIo: Executor = Executors.newFixedThreadPool(3)
) {

    private class MainThreadExecutor : Executor {
        private val mainThreadHandler = Handler(Looper.getMainLooper())

        override fun execute(command: Runnable) {
            mainThreadHandler.post(command)
        }
    }

    private class DiskIoThreadExecutor : Executor {
        private val diskIO = Executors.newSingleThreadExecutor()

        override fun execute(command: Runnable) {
            diskIO.execute(command)
        }
    }
}