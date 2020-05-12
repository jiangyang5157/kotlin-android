package com.gmail.jiangyang5157.kotlin.example.router

import com.gmail.jiangyang5157.android.router.fragment.FragmentRouter
import com.gmail.jiangyang5157.android.router.fragment.FragmentRouterBuilder
import com.gmail.jiangyang5157.kotlin.example.router.ui.uri.route.UriRouterData
import com.gmail.jiangyang5157.kotlin.example.router.ui.uri.route.UriRoute

object RouterApi {

    private val uriRouterData = UriRouterData()

    lateinit var uriRouter: FragmentRouter<UriRoute>

    fun setupUriRouter() {
        val builder = FragmentRouterBuilder(UriRoute::class)
        builder.transitions {
            uriRouterData.fragmentTransitions.forEach {
                register(it)
            }
        }
        builder.routing {
            uriRouterData.routes.forEach {
                route(it.routeClass) { it.fragmentClass }
            }
        }
        uriRouter = builder.build()
    }

    fun uriRoute(uriString: String): UriRoute =
        uriRouterData.routes.first { it.accept(uriString) }
            .routeClass.java.getDeclaredConstructor(String::class.java)
            .newInstance(uriString)
}
