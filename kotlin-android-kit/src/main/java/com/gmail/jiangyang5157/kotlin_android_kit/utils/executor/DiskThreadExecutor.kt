package com.gmail.jiangyang5157.kotlin_android_kit.utils.executor

import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * Created by Yang Jiang on April 19, 2018
 */
open class DiskThreadExecutor : Executor {
    private val io = Executors.newSingleThreadExecutor()

    override fun execute(command: Runnable) {
        io.execute(command)
    }
}