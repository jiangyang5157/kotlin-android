package com.gmail.jiangyang5157.android.router.fragment

import android.os.Bundle
import com.gmail.jiangyang5157.android.router.core.Route
import com.gmail.jiangyang5157.android.router.core.RoutingStack

interface FragmentRoutingStackBundleSyntax<T : Route> {
    fun RoutingStack<T>.saveTo(outState: Bundle)
    fun Bundle.restore(): RoutingStack<T>?
}
