package com.gmail.jiangyang5157.android.router.fragment

import com.gmail.jiangyang5157.android.router.core.Route
import com.gmail.jiangyang5157.android.router.fragment.setup.FragmentRouteStorageSyntax

internal interface FragmentRouterConfiguration<T : Route> {
    val fragmentMap: FragmentMap<T>
    val fragmentRouteStorageSyntax: FragmentRouteStorageSyntax<T>
    val fragmentRoutingStackBundleSyntax: FragmentRoutingStackBundleSyntax<T>
}
