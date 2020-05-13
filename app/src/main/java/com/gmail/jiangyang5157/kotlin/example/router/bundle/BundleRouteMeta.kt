package com.gmail.jiangyang5157.kotlin.example.router.bundle

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import com.gmail.jiangyang5157.android.router.core.Route
import kotlin.reflect.KClass

interface BundleRoute : Route, Parcelable

data class BundleRouteData(val id: String, val data: Bundle)

data class BundleRouteMeta(
    val fragmentClass: KClass<out Fragment>,
    val routeClass: KClass<out BundleRoute>,
    val id: String
) {

    fun accept(bundleRouteData: BundleRouteData): Boolean = this.id == bundleRouteData.id
}
