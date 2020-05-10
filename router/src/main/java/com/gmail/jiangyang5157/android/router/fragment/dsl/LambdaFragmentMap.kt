package com.gmail.jiangyang5157.android.router.fragment.dsl

import androidx.fragment.app.Fragment
import com.gmail.jiangyang5157.android.router.core.Route
import com.gmail.jiangyang5157.android.router.fragment.FragmentMap
import kotlin.reflect.KClass

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