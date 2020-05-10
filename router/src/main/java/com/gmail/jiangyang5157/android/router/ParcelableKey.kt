package com.gmail.jiangyang5157.android.router

import android.os.Parcelable
import com.gmail.jiangyang5157.android.router.core.Key
import com.gmail.jiangyang5157.android.router.core.randomKeyValue
import kotlinx.android.parcel.Parcelize

/**
 * Wrapper class for [Key] in order to make [Key] implement [Parcelable]
 */
@Parcelize
data class ParcelableKey(override val value: String = randomKeyValue()) : Key(), Parcelable

/**
 * @return A [ParcelableKey] wrapper or the same instance if this already is a [ParcelableKey]
 */
fun Key.parcelable(): ParcelableKey {
    return when (this) {
        is ParcelableKey -> this
        else -> ParcelableKey(value)
    }
}