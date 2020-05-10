package com.gmail.jiangyang5157.android.router.core

/**
 * # MultiRouter
 * Convenience class that allows for a similar usage than the "Router" interface of previous versions of the library.
 * It basically stores (and creates) references to routers for a specified keys
 *
 * ## Note
 * - This class is fully thread safe on the JVM.
 * - This function will only be called once for each key and the resulting [Router] will be stored
 *
 * @param factory: Function that creates a router for a specific key.
 */
class MultiRouter<K, T : Route>(private val factory: (key: K) -> Router<T>) {

    private val routers = mutableMapOf<K, Router<T>>()

    private val lock = Lock()

    operator fun get(key: K): Router<T> = lock {
        routers.getOrPut(key, { factory(key) })
    }
}
