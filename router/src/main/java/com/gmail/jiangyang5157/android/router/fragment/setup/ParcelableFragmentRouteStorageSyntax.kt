package com.gmail.jiangyang5157.android.router.fragment.setup

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import com.gmail.jiangyang5157.android.router.core.ParcelableRoute
import com.gmail.jiangyang5157.android.router.core.Route
import com.gmail.jiangyang5157.android.router.error.MissingRouteException

internal fun <T : Route> ParcelableFragmentRouteStorageSyntax.Companion.createUnsafe():
    FragmentRouteStorageSyntax<T> {

    @Suppress("UNCHECKED_CAST")
    return ParcelableFragmentRouteStorageSyntax<ParcelableRoute>() as FragmentRouteStorageSyntax<T>
}

class ParcelableFragmentRouteStorageSyntax<T>(
    private val bundleKey: String = DEFAULT_KEY_BUNDLE_DATA
) : FragmentRouteStorageSyntax<T> where T : Parcelable, T : Route {

    override fun Fragment.attach(route: T) {
        val arguments = this.arguments ?: Bundle()
        arguments.putParcelable(bundleKey, route)
        this.arguments = arguments
    }

    override fun Fragment.getRouteOrNull(): T? =
        arguments?.getParcelable(bundleKey)

    override fun Fragment.getRoute(): T =
        getRouteOrNull() ?: throw MissingRouteException(
            "Expected route with key $bundleKey"
        )

    companion object {

        const val DEFAULT_KEY_BUNDLE_DATA = "bundle_data"
    }
}
