package com.gmail.jiangyang5157.android.router.fragment.setup

import androidx.fragment.app.Fragment
import com.gmail.jiangyang5157.android.router.core.Route
import kotlin.reflect.KClass

/**
 * # FragmentMap
 * Definition of which fragment should be displayed for a certain route
 */
interface FragmentMap<in T : Route> {

    /**
     * @return
     * - The class of the fragment that should be displayed for the [route]
     * - `null` if this map does not contain any information about the given [route]
     */
    operator fun get(route: T): KClass<out Fragment>?
}

class EmptyFragmentMap<T : Route> :
    FragmentMap<T> {
    override fun get(route: T): KClass<out Fragment>? = null
}

@PublishedApi
internal class LambdaFragmentMap<T : Route, R : T>(
    private val type: KClass<R>,
    private val lambda: R.() -> KClass<out Fragment>?
) : FragmentMap<T> {

    override fun get(route: T): KClass<out Fragment>? {
        return if (type.java.isInstance(route)) {
            @Suppress("UNCHECKED_CAST")
            lambda(route as R)
        } else {
            null
        }
    }
}

internal class CompositeFragmentMap<T : Route>(
    private val first: FragmentMap<T>,
    private val second: FragmentMap<T>
) : FragmentMap<T> {

    override fun get(route: T): KClass<out Fragment>? {
        return first[route] ?: second[route]
    }
}
