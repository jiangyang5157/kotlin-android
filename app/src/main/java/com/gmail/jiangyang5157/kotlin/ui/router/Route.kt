package com.gmail.jiangyang5157.kotlin.ui.router

import android.os.Parcelable
import com.gmail.jiangyang5157.android.router.core.Route
import kotlinx.android.parcel.Parcelize

sealed class AppRoute : Route, Parcelable

@Parcelize
data class Route0(val info: String) : AppRoute()

@Parcelize
data class Route1(val info: String) : AppRoute()

@Parcelize
data class Route2(val info: String) : AppRoute()

@Parcelize
data class Route3(val info: String) : AppRoute()
