package com.gmail.jiangyang5157.android.router

import android.os.Parcelable
import com.gmail.jiangyang5157.android.router.core.Route
import com.gmail.jiangyang5157.android.router.core.RoutingStack
import kotlinx.android.parcel.Parcelize

/**
 * [RoutingStack.Element] implementation that also implements [Parcelable]
 */
@Parcelize
data class ParcelableElement<T>(
    override val key: ParcelableKey,
    override val route: T
) : RoutingStack.Element<T>(), Parcelable where T : Route, T : Parcelable

/**
 * Wraps all elements using the `Element.parcelable` function.
 */
fun <T> Iterable<RoutingStack.Element<T>>.parcelable() where T : Route, T : Parcelable =
    this.map { entry -> entry.parcelable() }
