package com.gmail.jiangyang5157.android.router.core

import com.gmail.jiangyang5157.android.router.error.MissingRouteException
import kotlin.reflect.KClass

/**
 * # GetRouteSyntax
 * Allows to retrieve the currently associated route from the router.
 *
 * ## Note
 * - Targets of routes typically implement this syntax.
 * - Fragment implements this syntax which allows the fragment to retrieve the route for which the fragment was created from the router.
 */
interface GetRouteSyntax {

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
     * @throws MissingRouteException if the route is not the correct type or cannot be found
     */
    fun <R : Route> getRoute(clazz: KClass<R>): R {
        return getRouteOrNull(clazz) ?: throw MissingRouteException(
            "Route ${clazz.java.simpleName} missing from $this"
        )
    }
}

/**
 * @see GetRouteSyntax.getRoute
 */
inline fun <reified T : Route> GetRouteSyntax.getRoute(): T = getRoute(T::class)

/**
 * @see GetRouteSyntax.getRouteOrNull
 */
inline fun <reified T : Route> GetRouteSyntax.getRouteOrNull(): T? = getRouteOrNull(T::class)

/**
 * Lazy version of [getRoute]
 */
inline fun <reified T : Route> GetRouteSyntax.route() = lazy { getRoute(T::class) }

/**
 * Lazy version of [getRouteOrNull]
 */
inline fun <reified T : Route> GetRouteSyntax.routeOrNull() = lazy { getRouteOrNull(T::class) }
