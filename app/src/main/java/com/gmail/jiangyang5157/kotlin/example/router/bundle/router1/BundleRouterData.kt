package com.gmail.jiangyang5157.kotlin.example.router.bundle.router1

import com.gmail.jiangyang5157.android.router.fragment.transition.FragmentTransition
import com.gmail.jiangyang5157.kotlin.example.router.bundle.*
import com.gmail.jiangyang5157.kotlin.example.router.transition.FadeFragmentTransition

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
