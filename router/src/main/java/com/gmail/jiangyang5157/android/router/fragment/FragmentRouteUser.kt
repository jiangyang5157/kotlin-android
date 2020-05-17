package com.gmail.jiangyang5157.android.router.fragment

import com.gmail.jiangyang5157.android.router.core.RouteUser
import com.gmail.jiangyang5157.android.router.core.Route
import com.gmail.jiangyang5157.android.router.fragment.host.AsFragment
import com.gmail.jiangyang5157.android.router.fragment.host.expectThisToBeAFragment
import kotlin.reflect.KClass

interface FragmentRouteUser :
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
