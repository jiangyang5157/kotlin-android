package com.gmail.jiangyang5157.android.router.fragment.setup

import com.gmail.jiangyang5157.android.router.core.Route

internal interface FragmentRouterConfiguration<T : Route> {
    val fragmentMap: FragmentMap<T>
    val fragmentRouteStorageSyntax: FragmentRouteStorageSyntax<T>
    val fragmentRoutingStackBundleSyntax: FragmentRoutingStackBundleSyntax<T>
}
