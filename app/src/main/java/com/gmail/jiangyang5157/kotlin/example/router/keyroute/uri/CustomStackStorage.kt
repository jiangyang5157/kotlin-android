package com.gmail.jiangyang5157.kotlin.example.router.keyroute.uri

import android.os.Bundle
import android.os.Parcelable
import com.gmail.jiangyang5157.android.router.core.*
import com.gmail.jiangyang5157.android.router.fragment.setup.RoutingStackStorage

/**
 * This class demonstrates how we can use a custom stack storage
 *
 * @see ParcelableRoutingStackStorage The Default implementation
 */
class CustomStackStorage<T> : RoutingStackStorage<T> where T : Route, T : Parcelable {

    override fun RoutingStack<T>.saveTo(outState: Bundle) {
        outState.putParcelable(KEY_ROUTING_STACK, this.parcelable())
    }

    override fun Bundle.restore(): RoutingStack<T>? =
        getParcelable<ParcelableRoutingStack<T>>(KEY_ROUTING_STACK)

    companion object {
        const val KEY_ROUTING_STACK = "CustomStackStorage"
    }
}
