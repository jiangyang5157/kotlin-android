package com.gmail.jiangyang5157.android.router.core

/**
 * [Route] implementation that has [data]
 */
interface DataRoute<T> : Route {

    val data: T
}
