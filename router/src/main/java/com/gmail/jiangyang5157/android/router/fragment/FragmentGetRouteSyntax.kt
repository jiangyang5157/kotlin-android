package com.gmail.jiangyang5157.android.router.fragment

import com.gmail.jiangyang5157.android.router.GetRouteSyntax
import com.gmail.jiangyang5157.android.router.core.Route
import com.gmail.jiangyang5157.android.router.fragment.setup.FragmentExtensions
import com.gmail.jiangyang5157.android.router.fragment.setup.expectThisToBeAFragment
import kotlin.reflect.KClass

interface FragmentGetRouteSyntax : GetRouteSyntax,
    FragmentExtensions {
    override val router: FragmentRouter<*>

    override fun <R : Route> getRouteOrNull(clazz: KClass<R>): R? {
        val route =
            router.fragmentRouteStorageSyntax.run { expectThisToBeAFragment().getRouteOrNull() }
        if (clazz.java.isInstance(route)) {
            @Suppress("UNCHECKED_CAST")
            return route as? R
        }
        return null
    }
}
