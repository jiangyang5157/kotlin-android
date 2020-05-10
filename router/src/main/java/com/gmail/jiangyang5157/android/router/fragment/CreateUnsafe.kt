package com.gmail.jiangyang5157.android.router.fragment

import android.os.Parcelable
import com.gmail.jiangyang5157.android.router.core.Route

internal fun <T : Route> ParcelableFragmentRouteStorageSyntax.Companion.createUnsafe(): FragmentRouteStorageSyntax<T> {
    @Suppress("UNCHECKED_CAST")
    return ParcelableFragmentRouteStorageSyntax<ParcelableRoute>() as FragmentRouteStorageSyntax<T>
}


internal fun <T : Route> ParcelableFragmentRoutingStackBundleSyntax.Companion.createUnsafe(
    key: String = KEY_DEFAULT
): FragmentRoutingStackBundleSyntax<T> {
    @Suppress("UNCHECKED_CAST")
    return ParcelableFragmentRoutingStackBundleSyntax<ParcelableRoute>(key) as FragmentRoutingStackBundleSyntax<T>
}

/**
 * Just a private interface to trick the compiler :)
 */
private interface ParcelableRoute : Route, Parcelable
