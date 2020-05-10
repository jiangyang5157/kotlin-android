package com.gmail.jiangyang5157.android.router.fragment

import androidx.fragment.app.Fragment
import com.gmail.jiangyang5157.android.router.core.Route
import com.gmail.jiangyang5157.android.router.core.RoutingStack
import kotlin.reflect.KClass

abstract class FragmentElement<T : Route> : RoutingStack.Element<T>() {

    interface Factory<T : Route> {
        operator fun invoke(element: RoutingStack.Element<T>): FragmentElement<T>
    }

    abstract fun createFragment(): Fragment
}

operator fun <T : Route> FragmentMap<T>.plus(other: FragmentMap<T>): FragmentMap<T> {
    return CompositeFragmentMap(
        this,
        other
    )
}

private class CompositeFragmentMap<T : Route>(
    private val first: FragmentMap<T>,
    private val second: FragmentMap<T>
) : FragmentMap<T> {

    override fun get(route: T): KClass<out Fragment>? {
        return first[route] ?: second[route]
    }
}
