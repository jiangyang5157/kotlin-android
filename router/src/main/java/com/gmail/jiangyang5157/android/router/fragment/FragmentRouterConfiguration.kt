package com.gmail.jiangyang5157.android.router.fragment

import com.gmail.jiangyang5157.android.router.core.Route
import com.gmail.jiangyang5157.android.router.fragment.map.FragmentMap

internal interface FragmentRouterConfiguration<T : Route> {
    val fragmentMap: FragmentMap
    val fragmentRouteStorage: FragmentRouteStorage<T>
    val saveRoutingStack: SaveRoutingStack<T>
}
