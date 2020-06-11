package com.gmail.jiangyang5157.android.router.core

import android.os.Parcelable
import com.gmail.jiangyang5157.kotlin_kit.data.model.Key
import kotlinx.android.parcel.Parcelize

/**
 * [Key] implementation that also implements [Parcelable]
 */
@Parcelize
data class ParcelableKey(override val value: String = randomKeyValue()) : Key(), Parcelable

/**
 * @return A [ParcelableKey] or the same instance if this already is a [ParcelableKey]
 */
fun Key.parcelable(): ParcelableKey =
    when (this) {
        is ParcelableKey -> this
        else -> ParcelableKey(value)
    }
