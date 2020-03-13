package com.gmail.jiangyang5157.architecture.util

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Yang Jiang on July 11, 2019
 */
@Singleton
open class AppExecutor(
  val mainThread: Executor,
  val diskIO: Executor,
  val networkIO: Executor
) {

    @Inject
    constructor() : this(
        MainThreadExecutor(),
            Executors.newSingleThreadExecutor(),
            Executors.newFixedThreadPool(2)
    )

    private class MainThreadExecutor : Executor {

        private val mainThreadHandler: Handler = Handler(Looper.getMainLooper())

        override fun execute(command: Runnable) {
            mainThreadHandler.post(command)
        }
    }
}
