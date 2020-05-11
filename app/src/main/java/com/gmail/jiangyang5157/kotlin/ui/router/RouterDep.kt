package com.gmail.jiangyang5157.kotlin.ui.router

import android.net.Uri
import android.os.Parcelable
import android.util.Log
import androidx.fragment.app.Fragment
import com.gmail.jiangyang5157.android.router.core.*
import com.gmail.jiangyang5157.android.router.fragment.FragmentRouter
import com.gmail.jiangyang5157.android.router.fragment.FragmentRouterBuilder
import kotlinx.android.parcel.Parcelize
import kotlin.reflect.KClass

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
        UriRoute0::class,
        RouterFragment0::class
    ),
    Route1(
        "https://com.gmail.jiangyang5157/RouterActivity/router1",
        UriRoute1::class,
        RouterFragment1::class
    ),
    Route2(
        "https://com.gmail.jiangyang5157/RouterActivity/router2",
        UriRoute2::class,
        RouterFragment2::class
    ),
    Route3(
        "https://com.gmail.jiangyang5157/RouterActivity/router3",
        UriRoute3::class,
        RouterFragment3::class
    );

    private fun accept(anotherUriString: String): Boolean {
        val uri = Uri.parse(address)
        val anotherUri = Uri.parse(anotherUriString)
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

sealed class UriRoute(open val uri: String) : Route, Parcelable

@Parcelize
data class UriRoute0(override val uri: String) : UriRoute(uri) {
    fun info(): String? = Uri.parse(uri).getQueryParameter("info")
}

@Parcelize
data class UriRoute1(override val uri: String) : UriRoute(uri) {
    fun info(): String? = Uri.parse(uri).getQueryParameter("info")
}

@Parcelize
data class UriRoute2(override val uri: String) : UriRoute(uri) {
    fun info(): String? = Uri.parse(uri).getQueryParameter("info")
}

@Parcelize
data class UriRoute3(override val uri: String) : UriRoute(uri) {
    fun info(): String? = Uri.parse(uri).getQueryParameter("info")
}

//fun debug() {
//    val uri =
//        Uri.parse("https://com.gmail.jiangyang5157/RouterActivity/router1?info=111&info2=222")
//    val scheme = uri.scheme
//    val authority = uri.authority
//    val path = uri.path
//    val pathSegments = uri.pathSegments
//    val lastPathSegment = uri.lastPathSegment
//    val args = uri.queryParameterNames
//    val info = uri.getQueryParameter("info")
//    Log.d(
//        "####",
//        "uri: $uri\n" + // https://com.gmail.jiangyang5157/RouterActivity/router1?info=111&info2=222
//            "scheme: $scheme\n" + // https
//            "authority: $authority\n" + //  com.gmail.jiangyang5157
//            "path: $path\n" + // /RouterActivity/router1
//            "pathSegments: $pathSegments\n" + // [RouterActivity, router1]
//            "lastPathSegment: $lastPathSegment\n" + // router1
//            "args: $args\n" + // [info, info2]
//            "info: $info\n" // 111
//    )
//}
