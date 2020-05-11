package com.gmail.jiangyang5157.kotlin.ui.router

import android.net.Uri
import android.os.Parcelable
import androidx.fragment.app.Fragment
import com.gmail.jiangyang5157.android.router.core.*
import com.gmail.jiangyang5157.android.router.fragment.FragmentRouter
import com.gmail.jiangyang5157.android.router.fragment.FragmentRouterBuilder
import kotlinx.android.parcel.Parcelize
import kotlin.reflect.KClass

sealed class UriRoute(open val uriString: String) : Route, Parcelable

object RouterDep {

    lateinit var routerActivityRouter: FragmentRouter<UriRoute>

    fun setupRouterForRouterActivity() {
        val builder = FragmentRouterBuilder(UriRoute::class)
        builder.transitions {
            register(RouterFragmentTransition())
            register(RouterFragment1And3Transition())
        }
        builder.routing {
            UriRouteRepo.values().forEach {
                route(it.routeClass) { it.fragmentClass }
            }
        }
        routerActivityRouter = builder.build()
    }
}

enum class UriRouteRepo(
    private val address: String,
    val routeClass: KClass<out UriRoute>,
    val fragmentClass: KClass<out Fragment>
) {
    Route0(
        "https://com.gmail.jiangyang5157/RouterActivity/router0",
        RouteFragment0::class,
        RouterFragment0::class
    ),
    Route1(
        "https://com.gmail.jiangyang5157/RouterActivity/router1",
        RouteFragment1::class,
        RouterFragment1::class
    ),
    Route2(
        "https://com.gmail.jiangyang5157/RouterActivity/router2",
        RouteFragment2::class,
        RouterFragment2::class
    ),
    Route3(
        "https://com.gmail.jiangyang5157/RouterActivity/router3",
        RouteFragment3::class,
        RouterFragment3::class
    );

    private fun accept(uriString: String): Boolean {
        val uri = Uri.parse(address)
        val anotherUri = Uri.parse(uriString)
        return anotherUri.scheme == uri.scheme &&
            anotherUri.authority == uri.authority &&
            anotherUri.path == uri.path
    }

    companion object {

        fun build(dst: String): UriRoute =
            values().first { it.accept(dst) }
                .routeClass.java.getDeclaredConstructor(String::class.java)
                .newInstance(dst)
    }
}

@Parcelize
data class RouteFragment0(override val uriString: String) : UriRoute(uriString) {
    fun info(): String? = Uri.parse(uriString).getQueryParameter("info")
}

@Parcelize
data class RouteFragment1(override val uriString: String) : UriRoute(uriString) {
    fun info(): String? = Uri.parse(uriString).getQueryParameter("info")
}

@Parcelize
data class RouteFragment2(override val uriString: String) : UriRoute(uriString) {
    fun info(): String? = Uri.parse(uriString).getQueryParameter("info")
}

@Parcelize
data class RouteFragment3(override val uriString: String) : UriRoute(uriString) {
    fun info(): String? = Uri.parse(uriString).getQueryParameter("info")
}
