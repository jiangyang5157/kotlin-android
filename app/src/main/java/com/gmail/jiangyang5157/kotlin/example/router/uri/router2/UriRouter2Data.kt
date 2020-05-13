package com.gmail.jiangyang5157.kotlin.example.router.uri.router2

import com.gmail.jiangyang5157.android.router.fragment.transition.FragmentTransition
import com.gmail.jiangyang5157.kotlin.example.router.transition.FadeFragmentTransition
import com.gmail.jiangyang5157.kotlin.example.router.uri.*

class UriRouter2Data {

    val fragmentTransitions: List<FragmentTransition> = listOf(
        FadeFragmentTransition()
    )

    val routes: List<UriRouteMeta> = listOf(
        UriRouteMeta(
            UriRouterFragment0::class,
            UriRouterFragment0.Route::class,
            UriRouterFragment0.Route.ID
        ),
        UriRouteMeta(
            UriRouterFragment1::class,
            UriRouterFragment1.Route::class,
            UriRouterFragment1.Route.ID
        ),
        UriRouteMeta(
            UriRouterFragment2::class,
            UriRouterFragment2.Route::class,
            UriRouterFragment2.Route.ID
        ),
        UriRouteMeta(
            UriRouterFragment3::class,
            UriRouterFragment3.Route::class,
            UriRouterFragment3.Route.ID
        )
    )
}
