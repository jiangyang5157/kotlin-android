package com.gmail.jiangyang5157.android.router.fragment.setup

import androidx.fragment.app.Fragment
import com.gmail.jiangyang5157.android.router.core.Route
import com.gmail.jiangyang5157.android.router.core.RoutingStack

abstract class FragmentElement<T : Route> : RoutingStack.Element<T>() {

    interface Factory<T : Route> {
        operator fun invoke(element: RoutingStack.Element<T>): FragmentElement<T>
    }

    abstract fun createFragment(): Fragment
}

operator fun <T : Route> FragmentMap<T>.plus(other: FragmentMap<T>): FragmentMap<T> =
    CompositeFragmentMap(this, other)
