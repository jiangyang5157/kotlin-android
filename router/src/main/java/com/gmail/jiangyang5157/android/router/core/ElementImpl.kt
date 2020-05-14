package com.gmail.jiangyang5157.android.router.core

import com.gmail.jiangyang5157.kotlin_kit.model.Key

data class ElementImpl<T : Route>(
    override val route: T,
    override val key: Key = Key()
) : RoutingStack.Element<T>()
