package com.gmail.jiangyang5157.android.router.fragment

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import com.gmail.jiangyang5157.android.router.core.*
import com.gmail.jiangyang5157.android.router.utils.Constant

@Suppress("UNCHECKED_CAST")
internal fun <T : Route> ParcelableFragmentRoutingStackBundleSyntax.Companion.createUnsafe(
    key: String = KEY_ROUTING_STACK
): FragmentRoutingStackBundleSyntax<T> =
    ParcelableFragmentRoutingStackBundleSyntax<ParcelableRoute>(
        key
    ) as FragmentRoutingStackBundleSyntax<T>


class ParcelableFragmentRoutingStackBundleSyntax<T>(
    private val key: String = KEY_ROUTING_STACK
) : FragmentRoutingStackBundleSyntax<T> where T : Route, T : Parcelable {

    override fun RoutingStack<T>.saveTo(outState: Bundle) {
        Log.d(Constant.TAG, "saving routes: ${this.routes.joinToString(", ")}")
        outState.putParcelable(key, parcelable())
    }

    override fun Bundle.restore(): RoutingStack<T>? =
        getParcelable<ParcelableRoutingStack<T>>(key).also {
            Log.d(Constant.TAG, "restored routes: ${it?.routes?.joinToString(", ")}")
        }

    companion object {
        const val KEY_ROUTING_STACK = "routing_stack"
    }
}
