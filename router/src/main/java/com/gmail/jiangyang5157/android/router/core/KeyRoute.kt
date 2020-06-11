package com.gmail.jiangyang5157.android.router.core

import com.gmail.jiangyang5157.kotlin_kit.data.model.Key

/**
 * [Route] implementation that has [key]
 */
interface KeyRoute : Route {
    val key: Key
}
