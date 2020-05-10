package com.gmail.jiangyang5157.android.router.fragment

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import com.gmail.jiangyang5157.android.router.core.Route
import com.gmail.jiangyang5157.android.router.error.MissingRouteException

class ParcelableFragmentRouteStorageSyntax<T>(
    private val bundleKey: String = KEY_DEFAULT
) : FragmentRouteStorageSyntax<T> where T : Parcelable, T : Route {

    override fun Fragment.attach(route: T) {
        val arguments = this.arguments ?: Bundle()
        arguments.putParcelable(bundleKey, route)
        this.arguments = arguments
    }

    override fun Fragment.getRouteOrNull(): T? {
        return arguments?.getParcelable(bundleKey)
    }

    override fun Fragment.getRoute(): T {
        return getRouteOrNull() ?: throw MissingRouteException(
            "Expected route with key $bundleKey"
        )
    }

    companion object {

        const val KEY_DEFAULT = "bundle_data"
    }
}
