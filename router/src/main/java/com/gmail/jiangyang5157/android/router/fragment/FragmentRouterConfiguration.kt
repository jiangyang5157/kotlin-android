package com.gmail.jiangyang5157.android.router.fragment

import com.gmail.jiangyang5157.android.router.core.Route
import com.gmail.jiangyang5157.android.router.fragment.mapping.FragmentMap

internal interface FragmentRouterConfiguration<T : Route> {
    val fragmentMap: FragmentMap
    val fragmentRouteStorage: FragmentRouteStorage<T>
    val routingStackStorage: RoutingStackStorage<T>
}
