package com.gmail.jiangyang5157.android.router.fragment

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import com.gmail.jiangyang5157.android.router.core.ParcelableRoute
import com.gmail.jiangyang5157.android.router.ParcelableRoutingStack
import com.gmail.jiangyang5157.android.router.core.Route
import com.gmail.jiangyang5157.android.router.core.RoutingStack
import com.gmail.jiangyang5157.android.router.core.routes
import com.gmail.jiangyang5157.android.router.utils.Constant

internal fun <T : Route> ParcelableFragmentRoutingStackBundleSyntax.Companion.createUnsafe(
    key: String = DEFAULT_KEY_SAVED_ROUTES
): FragmentRoutingStackBundleSyntax<T> {

    @Suppress("UNCHECKED_CAST")
    return ParcelableFragmentRoutingStackBundleSyntax<ParcelableRoute>(key) as FragmentRoutingStackBundleSyntax<T>
}

class ParcelableFragmentRoutingStackBundleSyntax<T>(
    private val key: String = DEFAULT_KEY_SAVED_ROUTES
) : FragmentRoutingStackBundleSyntax<T> where T : Route, T : Parcelable {

    override fun RoutingStack<T>.saveTo(outState: Bundle) {
        Log.d(Constant.TAG, "saving routes: ${this.routes.joinToString(", ")}")
        outState.putParcelable(key, parcelable())
    }

    override fun Bundle.restore(): RoutingStack<T>? {
        return getParcelable<ParcelableRoutingStack<T>>(key).also {
            Log.d(Constant.TAG, "restored routes: ${it?.routes?.joinToString(", ")}")
        }
    }

    companion object {

        const val DEFAULT_KEY_SAVED_ROUTES = "saved_routes"
    }
}
