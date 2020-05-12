package com.gmail.jiangyang5157.android.router.fragment

import androidx.fragment.app.Fragment
import com.gmail.jiangyang5157.android.router.core.Route
import kotlin.reflect.KClass

internal class CompositeFragmentMap<T : Route>(
    private val first: FragmentMap<T>,
    private val second: FragmentMap<T>
) : FragmentMap<T> {

    override fun get(route: T): KClass<out Fragment>? = first[route] ?: second[route]
}
