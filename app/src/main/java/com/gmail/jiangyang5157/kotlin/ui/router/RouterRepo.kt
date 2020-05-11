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
            "https://com.gmail.jiangyang5157/RouterActivity/router0",
            RouterFragment0.Route::class,
            RouterFragment0::class
        ),
        UriRouteMeta(
            "https://com.gmail.jiangyang5157/RouterActivity/router1",
            RouterFragment1.Route::class,
            RouterFragment1::class
        ),
        UriRouteMeta(
            "https://com.gmail.jiangyang5157/RouterActivity/router2",
            RouterFragment2.Route::class,
            RouterFragment2::class
        ),
        UriRouteMeta(
            "https://com.gmail.jiangyang5157/RouterActivity/router3",
            RouterFragment3.Route::class,
            RouterFragment3::class
        )
    )

    data class UriRouteMeta(
        val address: String,
        val routeClass: KClass<out UriRoute>,
        val fragmentClass: KClass<out Fragment>
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
