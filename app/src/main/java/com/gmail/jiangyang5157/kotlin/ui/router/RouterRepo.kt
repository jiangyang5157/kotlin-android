package com.gmail.jiangyang5157.kotlin.ui.router

import android.net.Uri
import android.os.Parcelable
import androidx.fragment.app.Fragment
import com.gmail.jiangyang5157.android.router.core.*
import kotlin.reflect.KClass

class RouterRepo {

    // FragmentTransition logic, to be used during route transitions
    val fragmentTransitions = listOf(
        RouterFragmentTransition(),
        RouterFragment1And3Transition()
    )

    // Routes' mata
    val routes = listOf(
        UriRouteMeta(
            RouterFragment0::class,
            RouterFragment0.Route::class,
            RouterFragment0.Route.ADDRESS
        ),
        UriRouteMeta(
            RouterFragment1::class,
            RouterFragment1.Route::class,
            RouterFragment1.Route.ADDRESS
        ),
        UriRouteMeta(
            RouterFragment2::class,
            RouterFragment2.Route::class,
            RouterFragment2.Route.ADDRESS
        ),
        UriRouteMeta(
            RouterFragment3::class,
            RouterFragment3.Route::class,
            RouterFragment3.Route.ADDRESS
        )
    )

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

    interface UriRoute : Route, Parcelable
}
