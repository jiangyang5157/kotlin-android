package com.gmail.jiangyang5157.kotlin.ui.router

import com.gmail.jiangyang5157.android.router.fragment.FragmentRouter
import com.gmail.jiangyang5157.android.router.fragment.FragmentRouterBuilder

object RouterApi {

    private val routerRepo = RouterRepo()

    lateinit var router: FragmentRouter<RouterRepo.UriRoute>

    fun setupRouterForRouterActivity() {
        val builder = FragmentRouterBuilder(RouterRepo.UriRoute::class)
        builder.transitions {
            routerRepo.fragmentTransitions.forEach {
                register(it)
            }
        }
        builder.routing {
            routerRepo.routes.forEach {
                route(it.routeClass) { it.fragmentClass }
            }
        }
        router = builder.build()
    }

    fun route(uriString: String): RouterRepo.UriRoute =
        routerRepo.routes.first { it.accept(uriString) }
            .routeClass.java.getDeclaredConstructor(String::class.java)
            .newInstance(uriString)
}
