package com.gmail.jiangyang5157.android.router.fragment

import com.gmail.jiangyang5157.android.router.core.Route

internal interface FragmentRouterConfiguration<T : Route> {
    val fragmentMap: FragmentMap<T>
    val fragmentRouteStorage: FragmentRouteStorage<T>
    val saveRoutingStack: SaveRoutingStack<T>
}
