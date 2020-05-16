package com.gmail.jiangyang5157.android.router.core

import com.gmail.jiangyang5157.android.router.error.RouterException
import kotlin.reflect.KClass

/**
 * # RouteUser
 * Allows to retrieve the currently associated route from the router.
 *
 * ## Note
 * - Targets of routes typically implement this.
 * - Fragment implements this which allows the fragment to retrieve the route for which the fragment was created from the router.
 */
interface RouteUser {

    val router: Router<*>

    /**
     * @return
     * - The associated route as type of [clazz] if possible.
     * - Null if the type does not match, or the route was not found
     */
    fun <R : Route> getRouteOrNull(clazz: KClass<R>): R?


    /**
     * @return the associated route as type of [clazz].
     *
     * @throws RouterException if the route is not the correct type or cannot be found
     */
    fun <R : Route> getRoute(clazz: KClass<R>): R =
        getRouteOrNull(clazz) ?: throw RouterException(
            "Route ${clazz.java.simpleName} missing from $this"
        )
}

/**
 * @see RouteUser.getRoute
 */
inline fun <reified T : Route> RouteUser.getRoute(): T = getRoute(T::class)

/**
 * @see RouteUser.getRouteOrNull
 */
inline fun <reified T : Route> RouteUser.getRouteOrNull(): T? = getRouteOrNull(T::class)

/**
 * Lazy version of [getRoute]
 */
inline fun <reified T : Route> RouteUser.route() = lazy { getRoute(T::class) }

/**
 * Lazy version of [getRouteOrNull]
 */
inline fun <reified T : Route> RouteUser.routeOrNull() = lazy { getRouteOrNull(T::class) }
