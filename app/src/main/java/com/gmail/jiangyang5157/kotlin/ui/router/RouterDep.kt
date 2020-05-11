package com.gmail.jiangyang5157.kotlin.ui.router

import android.net.Uri
import android.os.Parcelable
import android.util.Log
import com.gmail.jiangyang5157.android.router.core.*
import com.gmail.jiangyang5157.android.router.fragment.FragmentRouter
import com.gmail.jiangyang5157.android.router.fragment.FragmentRouterBuilder
import kotlinx.android.parcel.Parcelize

object RouterDep {

    fun debug() {
        val uri =
            Uri.parse("https://com.gmail.jiangyang5157/RouterActivity/router1?info=111&info2=222")
        val scheme = uri.scheme
        val authority = uri.authority
        val path = uri.path
        val pathSegments = uri.pathSegments
        val lastPathSegment = uri.lastPathSegment
        val args = uri.queryParameterNames
        val info = uri.getQueryParameter("info")
        Log.d(
            "####",
            "uri: $uri\n" + // https://com.gmail.jiangyang5157/RouterActivity/router1?info=111&info2=222
                "scheme: $scheme\n" + // https
                "authority: $authority\n" + //  com.gmail.jiangyang5157
                "path: $path\n" + // /RouterActivity/router1
                "pathSegments: $pathSegments\n" + // [RouterActivity, router1]
                "lastPathSegment: $lastPathSegment\n" + // router1
                "args: $args\n" + // [info, info2]
                "info: $info\n" // 111
        )
    }

    lateinit var routerActivityRouter: FragmentRouter<UriRoute>

    fun setupRouterForRouterActivity() {
        val builder = FragmentRouterBuilder(UriRoute::class)
        builder.transitions {
            register(RouterFragmentTransition())
            register(RouterFragment1And3Transition())
        }
        builder.routing {
            route<UriRoute0> { RouterFragment0::class }
            route<UriRoute1> { RouterFragment1::class }
            route<UriRoute2> { RouterFragment2::class }
            route<UriRoute3> { RouterFragment3::class }
        }
        routerActivityRouter = builder.build()
    }
}

sealed class UriRoute(private val uriString: String) : Route, Parcelable {

    fun accept(anotherUriString: String): Boolean {
        val uri = Uri.parse(uriString)
        val anotherUri = Uri.parse(anotherUriString)
        return anotherUri.scheme == uri.scheme &&
            anotherUri.authority == uri.authority &&
            anotherUri.path == uri.path
    }
}

@Parcelize
data class UriRoute0(private val data: String) :
    UriRoute("https://com.gmail.jiangyang5157/RouterActivity/router0") {
    fun info(): String? = Uri.parse(data).getQueryParameter("info")
}

@Parcelize
data class UriRoute1(private val data: String) :
    UriRoute("https://com.gmail.jiangyang5157/RouterActivity/router1") {
    fun info(): String? = Uri.parse(data).getQueryParameter("info")
}

@Parcelize
data class UriRoute2(private val data: String) :
    UriRoute("https://com.gmail.jiangyang5157/RouterActivity/router2") {
    fun info(): String? = Uri.parse(data).getQueryParameter("info")
}

@Parcelize
data class UriRoute3(private val data: String) :
    UriRoute("https://com.gmail.jiangyang5157/RouterActivity/router3") {
    fun info(): String? = Uri.parse(data).getQueryParameter("info")
}
