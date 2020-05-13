package com.gmail.jiangyang5157.kotlin.example.router

import android.os.Bundle
import com.gmail.jiangyang5157.android.router.fragment.FragmentRouter
import com.gmail.jiangyang5157.android.router.fragment.FragmentRouterBuilder
import com.gmail.jiangyang5157.kotlin.example.router.bundle.BundleRoute
import com.gmail.jiangyang5157.kotlin.example.router.bundle.BundleRouteData
import com.gmail.jiangyang5157.kotlin.example.router.bundle.scope1.BundleRouterScope1
import com.gmail.jiangyang5157.kotlin.example.router.uri.UriRoute
import com.gmail.jiangyang5157.kotlin.example.router.uri.scope1.UriRouterScope1

object RouterApi {

    private val uriRouterScope1 = UriRouterScope1()
    private val bundleRouterScope1 = BundleRouterScope1()

    lateinit var uriRouter1: FragmentRouter<UriRoute>
    lateinit var bundleRouter1: FragmentRouter<BundleRoute>

    fun setupUriRouter1() {
        val builder = FragmentRouterBuilder(UriRoute::class)
        builder.transitions {
            uriRouterScope1.fragmentTransitions.forEach {
                register(it)
            }
        }
        builder.routing {
            uriRouterScope1.routes.forEach {
                route(it.routeClass) { it.fragmentClass }
            }
        }
        uriRouter1 = builder.build()
    }

    fun setupBundleRouter1() {
        val builder = FragmentRouterBuilder(BundleRoute::class)
        builder.transitions {
            bundleRouterScope1.fragmentTransitions.forEach {
                register(it)
            }
        }
        builder.routing {
            bundleRouterScope1.routes.forEach {
                route(it.routeClass) { it.fragmentClass }
            }
        }
        bundleRouter1 = builder.build()
    }

    fun uriRoute1(uriString: String): UriRoute =
        uriRouterScope1.routes.first { it.accept(uriString) }
            .routeClass.java.getDeclaredConstructor(String::class.java)
            .newInstance(uriString)

    fun bundleRoute1(bundleRouteData: BundleRouteData): BundleRoute =
        this.bundleRouterScope1.routes.first { it.accept(bundleRouteData) }
            .routeClass.java.getDeclaredConstructor(Bundle::class.java)
            .newInstance(bundleRouteData.data)
}
