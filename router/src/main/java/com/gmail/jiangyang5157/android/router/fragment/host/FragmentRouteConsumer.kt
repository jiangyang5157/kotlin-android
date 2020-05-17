package com.gmail.jiangyang5157.android.router.fragment.host

import com.gmail.jiangyang5157.android.router.core.RouteUser
import com.gmail.jiangyang5157.android.router.core.Route
import com.gmail.jiangyang5157.android.router.fragment.FragmentRouter
import kotlin.reflect.KClass

interface FragmentRouteConsumer :
    RouteUser,
    AsFragment {

    override val router: FragmentRouter<*>

    override fun <R : Route> getRouteOrNull(clazz: KClass<R>): R? {
        val route =
            router.fragmentRouteStorage.run { expectThisToBeAFragment().getRouteOrNull() }
        if (clazz.java.isInstance(route)) {
            @Suppress("UNCHECKED_CAST")
            return route as? R
        } else {
            return null
        }
    }
}
