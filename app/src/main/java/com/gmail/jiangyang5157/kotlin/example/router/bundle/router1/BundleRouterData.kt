package com.gmail.jiangyang5157.kotlin.example.router.bundle.router1

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import com.gmail.jiangyang5157.android.router.core.Route
import com.gmail.jiangyang5157.android.router.fragment.transition.FragmentTransition
import com.gmail.jiangyang5157.kotlin.example.router.bundle.BundleRouterFragment0
import com.gmail.jiangyang5157.kotlin.example.router.bundle.BundleRouterFragment1
import com.gmail.jiangyang5157.kotlin.example.router.bundle.BundleRouterFragment2
import com.gmail.jiangyang5157.kotlin.example.router.bundle.BundleRouterFragment3
import com.gmail.jiangyang5157.kotlin.example.router.transition.FadeFragmentTransition
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

class BundleRouterData {

    val fragmentTransitions: List<FragmentTransition> = listOf(
        FadeFragmentTransition()
    )

    val routes: List<BundleRouteMeta> = listOf(
        BundleRouteMeta(
            BundleRouterFragment0::class,
            BundleRouterFragment0.Route::class,
            BundleRouterFragment0.Route.ID
        ),
        BundleRouteMeta(
            BundleRouterFragment1::class,
            BundleRouterFragment1.Route::class,
            BundleRouterFragment1.Route.ID
        ),
        BundleRouteMeta(
            BundleRouterFragment2::class,
            BundleRouterFragment2.Route::class,
            BundleRouterFragment2.Route.ID
        ),
        BundleRouteMeta(
            BundleRouterFragment3::class,
            BundleRouterFragment3.Route::class,
            BundleRouterFragment3.Route.ID
        )
    )
}
