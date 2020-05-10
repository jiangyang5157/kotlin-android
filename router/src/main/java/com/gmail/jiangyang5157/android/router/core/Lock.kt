package com.gmail.jiangyang5157.android.router.core

import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

internal class Lock {

    private val reentrantLock = ReentrantLock()

    operator fun <T> invoke(action: () -> T): T = reentrantLock.withLock(action)
}
