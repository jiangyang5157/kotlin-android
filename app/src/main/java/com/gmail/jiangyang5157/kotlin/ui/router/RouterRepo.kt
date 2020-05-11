package com.gmail.jiangyang5157.kotlin.ui.router

import android.net.Uri
import android.os.Parcelable
import androidx.fragment.app.Fragment
import com.gmail.jiangyang5157.android.router.core.*
import kotlinx.android.parcel.Parcelize
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
            UriRoute.Fragment0::class,
            RouterFragment0::class
        ),
        UriRouteMeta(
            "https://com.gmail.jiangyang5157/RouterActivity/router1",
            UriRoute.Fragment1::class,
            RouterFragment1::class
        ),
        UriRouteMeta(
            "https://com.gmail.jiangyang5157/RouterActivity/router2",
            UriRoute.Fragment2::class,
            RouterFragment2::class
        ),
        UriRouteMeta(
            "https://com.gmail.jiangyang5157/RouterActivity/router3",
            UriRoute.Fragment3::class,
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
}


sealed class UriRoute(open val uriString: String) : Route, Parcelable {

    @Parcelize
    data class Fragment0(override val uriString: String) : UriRoute(uriString) {
        fun info() = Uri.parse(uriString).getQueryParameter("info")
    }

    @Parcelize
    data class Fragment1(override val uriString: String) : UriRoute(uriString) {
        fun info() = Uri.parse(uriString).getQueryParameter("info")
    }

    @Parcelize
    data class Fragment2(override val uriString: String) : UriRoute(uriString) {
        fun info() = Uri.parse(uriString).getQueryParameter("info")
    }

    @Parcelize
    data class Fragment3(override val uriString: String) : UriRoute(uriString) {
        fun info() = Uri.parse(uriString).getQueryParameter("info")
    }
}
