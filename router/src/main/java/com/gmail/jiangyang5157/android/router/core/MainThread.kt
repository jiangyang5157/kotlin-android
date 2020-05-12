package com.gmail.jiangyang5157.android.router.core

import android.os.Handler
import android.os.Looper
import com.gmail.jiangyang5157.android.router.error.WrongThreadException

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
        throw WrongThreadException(
            "Required main thread. Found: ${Thread.currentThread().id}: ${Thread.currentThread().name}"
        )
    }
}
