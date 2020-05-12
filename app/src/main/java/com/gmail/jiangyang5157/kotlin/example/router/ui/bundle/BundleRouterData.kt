package com.gmail.jiangyang5157.kotlin.example.router.ui.bundle

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import com.gmail.jiangyang5157.android.router.core.Route
import com.gmail.jiangyang5157.android.router.fragment.transition.FragmentTransition
import com.gmail.jiangyang5157.kotlin.example.router.ui.transition.FadeFragmentTransition
import kotlin.reflect.KClass

interface BundleRoute : Route, Parcelable

data class BundleData(val key: String, val param: Bundle)

data class BundleRouteMeta(
    val fragmentClass: KClass<out Fragment>,
    val routeClass: KClass<out BundleRoute>,
    val key: String
) {

    fun accept(key: String): Boolean = this.key == key
}

class BundleRouterData {

    val fragmentTransitions: List<FragmentTransition> = listOf(
        FadeFragmentTransition()
    )

    val routes: List<BundleRouteMeta> = listOf(
        BundleRouteMeta(
            BundleRouterFragment0::class,
            BundleRouterFragment0.Route::class,
            BundleRouterFragment0.Route.KEY
        ),
        BundleRouteMeta(
            BundleRouterFragment1::class,
            BundleRouterFragment1.Route::class,
            BundleRouterFragment1.Route.KEY
        ),
        BundleRouteMeta(
            BundleRouterFragment2::class,
            BundleRouterFragment2.Route::class,
            BundleRouterFragment2.Route.KEY
        ),
        BundleRouteMeta(
            BundleRouterFragment3::class,
            BundleRouterFragment3.Route::class,
            BundleRouterFragment3.Route.KEY
        )
    )
}
