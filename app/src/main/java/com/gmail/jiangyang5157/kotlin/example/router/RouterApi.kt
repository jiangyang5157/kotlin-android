package com.gmail.jiangyang5157.kotlin.example.router

import android.os.Bundle
import com.gmail.jiangyang5157.android.router.fragment.FragmentRouter
import com.gmail.jiangyang5157.android.router.fragment.FragmentRouterBuilder
import com.gmail.jiangyang5157.kotlin.example.router.ui.bundle.BundleData
import com.gmail.jiangyang5157.kotlin.example.router.ui.bundle.BundleRoute
import com.gmail.jiangyang5157.kotlin.example.router.ui.bundle.BundleRouterData
import com.gmail.jiangyang5157.kotlin.example.router.ui.uri.UriRouterData
import com.gmail.jiangyang5157.kotlin.example.router.ui.uri.UriRoute

object RouterApi {

    private val uriRouterData = UriRouterData()
    private val bundleRouterData = BundleRouterData()

    lateinit var uriRouter: FragmentRouter<UriRoute>
    lateinit var bundleRouter: FragmentRouter<BundleRoute>

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

    fun setupBundleRouter() {
        val builder = FragmentRouterBuilder(BundleRoute::class)
        builder.transitions {
            bundleRouterData.fragmentTransitions.forEach {
                register(it)
            }
        }
        builder.routing {
            bundleRouterData.routes.forEach {
                route(it.routeClass) { it.fragmentClass }
            }
        }
        bundleRouter = builder.build()
    }

    fun uriRoute(uriString: String): UriRoute =
        uriRouterData.routes.first { it.accept(uriString) }
            .routeClass.java.getDeclaredConstructor(String::class.java)
            .newInstance(uriString)

    fun bundleRoute(bundleData: BundleData): BundleRoute =
        bundleRouterData.routes.first { it.accept(bundleData.key) }
            .routeClass.java.getDeclaredConstructor(Bundle::class.java)
            .newInstance(bundleData.param)
}
