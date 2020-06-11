package com.gmail.jiangyang5157.android.router.core

import com.gmail.jiangyang5157.kotlin_kit.data.model.Key

/**
 * The default implementation of [RoutingStack.Element]
 */
data class RoutingStackElementImpl<T : Route>(
    override val route: T,
    override val key: Key = Key()
) : RoutingStack.Element<T>()
