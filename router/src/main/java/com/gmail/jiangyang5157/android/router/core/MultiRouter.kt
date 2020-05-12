package com.gmail.jiangyang5157.android.router.core

import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

/**
 * # MultiRouter
 * Convenience class that stores (and creates) references to routers for a specified keys
 *
 * ## Note
 * This class is fully thread safe on the JVM.
 *
 * @param factory: Function that creates a router for a specific key.
 * - This function will only be called once for each key and the resulting [Router] will be stored
 */
class MultiRouter<K, T : Route>(private val factory: (key: K) -> Router<T>) {

    private val routers = mutableMapOf<K, Router<T>>()

    private val lock = ReentrantLock()

    operator fun get(key: K): Router<T> = lock.withLock {
        routers.getOrPut(key, { factory(key) })
    }
}
