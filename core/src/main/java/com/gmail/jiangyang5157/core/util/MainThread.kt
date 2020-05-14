package com.gmail.jiangyang5157.core.util

import android.os.Handler
import android.os.Looper

internal inline fun <T> mainThread(crossinline action: () -> T) {
    if (isMainThread) {
        action()
    } else {
        mainThreadHandler.post { action() }
    }
}

internal val isMainThread: Boolean
    get() = Looper.getMainLooper() == Looper.myLooper()

internal val mainThreadHandler = Handler(Looper.getMainLooper())

internal fun requireMainThread() {
    if (!isMainThread) {
        throw RuntimeException(
            "Required main thread. Found: ${Thread.currentThread().id}: ${Thread.currentThread().name}"
        )
    }
}
