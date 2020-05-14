package com.gmail.jiangyang5157.android.router.fragment

import androidx.fragment.app.Fragment
import com.gmail.jiangyang5157.android.router.core.Route
import com.gmail.jiangyang5157.android.router.core.RoutingStack
import com.gmail.jiangyang5157.android.router.error.FragmentMappingMissingException
import com.gmail.jiangyang5157.kotlin_kit.model.Key
import kotlin.reflect.KClass

internal class FragmentElementImpl<T : Route>(
    private val fragmentRouterConfiguration: FragmentRouterConfiguration<T>,
    private val container: FragmentContainer,
    private val element: RoutingStack.Element<T>
) : FragmentElement<T>(),
    FragmentRouterConfiguration<T> by fragmentRouterConfiguration {

    class Factory<T : Route>(
        private val fragmentRouterConfiguration: FragmentRouterConfiguration<T>,
        private val container: FragmentContainer
    ) : FragmentElement.Factory<T> {
        override fun invoke(element: RoutingStack.Element<T>): FragmentElement<T> =
            FragmentElementImpl(
                fragmentRouterConfiguration = fragmentRouterConfiguration,
                container = container,
                element = element
            )
    }

    override val key: Key = element.key

    override val route: T = element.route

    override fun createFragment(): Fragment {
        val context = container.activity
        val fragmentFactory = container.fragmentManager.fragmentFactory
        val fragment =
            fragmentFactory.instantiate(context.classLoader, getFragmentClassNameOrThrow())
        fragmentRouteStorageSyntax.run { fragment.attach(route) }
        return fragment
    }

    private fun getFragmentClassNameOrThrow(): String =
        getFragmentClassOrThrow().java.canonicalName.orEmpty()

    private fun getFragmentClassOrThrow(): KClass<out Fragment> =
        if (route is FragmentRoute) {
            route.fragment
        } else {
            fragmentMap[route] ?: throw FragmentMappingMissingException(route)
        }
}
