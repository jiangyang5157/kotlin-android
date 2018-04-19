package com.gmail.jiangyang5157.kotlin_android_kit.utils.executor

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor

/**
 * Created by Yang Jiang on April 19, 2018
 */
open class MainThreadExecutor : Executor {
    private val io = Handler(Looper.getMainLooper())

    override fun execute(command: Runnable) {
        io.post(command)
    }
}