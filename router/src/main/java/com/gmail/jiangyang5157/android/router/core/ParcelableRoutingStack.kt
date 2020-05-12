package com.gmail.jiangyang5157.android.router.core

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

interface ParcelableRoutingStack<T : Route> : RoutingStack<T>, Parcelable

/**
 * @return [ParcelableRoutingStack] wrapper for the current stack, or this instance if it already implements [ParcelableRoutingStack]
 */
fun <T> RoutingStack<T>.parcelable(): ParcelableRoutingStack<T> where T : Route, T : Parcelable =
    when (this) {
        is ParcelableRoutingStack<T> -> this
        else -> ParcelableRoutingStackWrapper(
            this.elements.map { element -> element.parcelable() })
    }

@Parcelize
private class ParcelableRoutingStackWrapper<T>(override val elements: List<ParcelableElement<T>>) :
    ParcelableRoutingStack<T> where T : Route, T : Parcelable {

    override fun with(elements: Iterable<RoutingStack.Element<T>>): ParcelableRoutingStack<T> =
        ParcelableRoutingStackWrapper(elements.map { element -> element.parcelable() })
}
