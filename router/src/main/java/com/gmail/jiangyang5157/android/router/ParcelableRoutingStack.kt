package com.gmail.jiangyang5157.android.router

import android.os.Parcelable
import com.gmail.jiangyang5157.android.router.core.Route
import com.gmail.jiangyang5157.android.router.core.RoutingStack
import com.gmail.jiangyang5157.android.router.core.parcelable
import kotlinx.android.parcel.Parcelize

interface ParcelableRoutingStack<T : Route> : RoutingStack<T>, Parcelable

/**
 * @return [ParcelableRoutingStack] wrapper for the current stack, or this instance if it already implements [ParcelableRoutingStack]
 */
fun <T> RoutingStack<T>.parcelable(): ParcelableRoutingStack<T> where T : Route, T : Parcelable {
    return when (this) {
        is ParcelableRoutingStack<T> -> this
        else -> ParcelableRoutingStackWrapper(this.elements.map { element -> element.parcelable() })
    }
}

@Parcelize
private class ParcelableRoutingStackWrapper<T>(override val elements: List<ParcelableElement<T>>) :
    ParcelableRoutingStack<T> where T : Route, T : Parcelable {

    override fun with(elements: Iterable<RoutingStack.Element<T>>): ParcelableRoutingStack<T> {
        return ParcelableRoutingStackWrapper(elements.map { element -> element.parcelable() })
    }
}

/**
 * @return [ParcelableElement] wrapper for the current element of this instance if it already implements [ParcelableElement]
 */
fun <T> RoutingStack.Element<T>.parcelable(): ParcelableElement<T> where T : Route, T : Parcelable {
    return when (this) {
        is ParcelableElement -> this
        else -> ParcelableElement(key.parcelable(), route)
    }
}
