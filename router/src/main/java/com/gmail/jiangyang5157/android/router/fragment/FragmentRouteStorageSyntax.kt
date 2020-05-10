package com.gmail.jiangyang5157.android.router.fragment

import androidx.fragment.app.Fragment
import com.gmail.jiangyang5157.android.router.core.Route

/**
 * # FragmentRouteStorageSyntax
 * Defines a way of attaching a route to a fragment in a way, that this fragment can retrieve this router later.
 *
 * ## Usage
 * ```
 * fragmentRouteStorageSyntax.run { fragment.attach(route) } 
 * val route = fragmentRouteStorageSyntax.run {  fragment.getRouteOrNull() } 
 * ```
 *
 * @see FragmentGetRouteSyntax
 */
interface FragmentRouteStorageSyntax<T : Route> {

    fun Fragment.attach(route: T)

    fun Fragment.getRouteOrNull(): T?

    fun Fragment.getRoute(): T
}
