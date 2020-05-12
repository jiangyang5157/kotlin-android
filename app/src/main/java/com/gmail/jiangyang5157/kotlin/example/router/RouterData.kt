package com.gmail.jiangyang5157.kotlin.example.router

import android.net.Uri
import android.os.Parcelable
import androidx.fragment.app.Fragment
import com.gmail.jiangyang5157.android.router.core.*
import com.gmail.jiangyang5157.kotlin.example.router.ui.transition.FadeFragmentTransition
import com.gmail.jiangyang5157.kotlin.example.router.ui.uri.UriRouterFragment0
import com.gmail.jiangyang5157.kotlin.example.router.ui.uri.UriRouterFragment1
import com.gmail.jiangyang5157.kotlin.example.router.ui.uri.UriRouterFragment2
import com.gmail.jiangyang5157.kotlin.example.router.ui.uri.UriRouterFragment3
import com.gmail.jiangyang5157.kotlin.example.router.ui.uri.transition.RouterFragment1And3Transition
import com.gmail.jiangyang5157.kotlin.example.router.ui.uri.transition.RouterFragmentTransition
import kotlin.reflect.KClass

class RouterData {

    // FragmentTransition logic, to be used during route transitions
    val fragmentTransitions = listOf(
        FadeFragmentTransition(),
        RouterFragmentTransition(),
        RouterFragment1And3Transition()
    )

    // Routes' mata
    val routes = listOf(
        UriRouteMeta(
            UriRouterFragment0::class,
            UriRouterFragment0.Route::class,
            UriRouterFragment0.Route.ADDRESS
        ),
        UriRouteMeta(
            UriRouterFragment1::class,
            UriRouterFragment1.Route::class,
            UriRouterFragment1.Route.ADDRESS
        ),
        UriRouteMeta(
            UriRouterFragment2::class,
            UriRouterFragment2.Route::class,
            UriRouterFragment2.Route.ADDRESS
        ),
        UriRouteMeta(
            UriRouterFragment3::class,
            UriRouterFragment3.Route::class,
            UriRouterFragment3.Route.ADDRESS
        )
    )

    interface UriRoute : Route, Parcelable

    data class UriRouteMeta(
        val fragmentClass: KClass<out Fragment>,
        val routeClass: KClass<out UriRoute>,
        val address: String
    ) {

        fun accept(uriString: String): Boolean {
            val uri = Uri.parse(address)
            val anotherUri = Uri.parse(uriString)
            return anotherUri.scheme == uri.scheme &&
                anotherUri.authority == uri.authority &&
                anotherUri.path == uri.path
        }
    }
}
