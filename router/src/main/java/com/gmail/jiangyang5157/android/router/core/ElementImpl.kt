package com.gmail.jiangyang5157.android.router.core

internal data class ElementImpl<T : Route>(
    override val route: T,
    override val key: Key = Key()
) : RoutingStack.Element<T>()
