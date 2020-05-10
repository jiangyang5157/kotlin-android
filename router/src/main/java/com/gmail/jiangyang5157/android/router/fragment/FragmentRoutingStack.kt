package com.gmail.jiangyang5157.android.router.fragment

import com.gmail.jiangyang5157.android.router.core.Route
import com.gmail.jiangyang5157.android.router.core.RoutingStack

data class FragmentRoutingStack<T : Route>(
    override val elements: List<FragmentElement<T>>,
    private val factory: FragmentElement.Factory<T>
) : RoutingStack<T> {

    override fun with(elements: Iterable<RoutingStack.Element<T>>): FragmentRoutingStack<T> {
        return copy(elements = elements.map(factory::invoke))
    }

    companion object {

        operator fun <T : Route> invoke(
            elements: List<RoutingStack.Element<T>>,
            factory: FragmentElement.Factory<T>
        ): FragmentRoutingStack<T> = FragmentRoutingStack(elements.map(factory::invoke), factory)
    }
}
