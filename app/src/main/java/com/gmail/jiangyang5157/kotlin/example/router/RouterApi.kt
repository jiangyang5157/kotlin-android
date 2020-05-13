package com.gmail.jiangyang5157.kotlin.example.router

import com.gmail.jiangyang5157.android.router.core.KeyImpl
import com.gmail.jiangyang5157.android.router.fragment.FragmentRouter
import com.gmail.jiangyang5157.kotlin.example.router.transition.FadeFragmentTransition
import com.gmail.jiangyang5157.kotlin.example.router.uri.ui.*
import com.gmail.jiangyang5157.kotlin.example.router.usecase.RouterData
import com.gmail.jiangyang5157.kotlin.example.router.usecase.UriRoutePatch
import com.gmail.jiangyang5157.kotlin.example.router.usecase.UriRouterData
import java.lang.IllegalArgumentException
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

object RouterApi {

    private val routers = hashMapOf<String, RouterData<*>>()

    private val lock = ReentrantLock()

    operator fun get(id: String): RouterData<*> = lock.withLock {
        routers.getOrPut(id, {
            when (id) {
                "UriRouterActivity1" -> {
                    val routes = listOf(
                        UriRoutePatch(
                            UriRouterFragment0::class,
                            UriRouterFragment0.Route::class,
                            KeyImpl(UriRouterFragment0.Route.ID)
                        ),
                        UriRoutePatch(
                            UriRouterFragment1::class,
                            UriRouterFragment1.Route::class,
                            KeyImpl(UriRouterFragment1.Route.ID)
                        ),
                        UriRoutePatch(
                            UriRouterFragment2::class,
                            UriRouterFragment2.Route::class,
                            KeyImpl(UriRouterFragment2.Route.ID)
                        ),
                        UriRoutePatch(
                            UriRouterFragment3::class,
                            UriRouterFragment3.Route::class,
                            KeyImpl(UriRouterFragment3.Route.ID)
                        )
                    )
                    UriRouterData(
                        routes,
                        FragmentRouter {
                            transitions {
                                register(FadeFragmentTransition())
                            }
                            routing {
                                routes.forEach {
                                    route(it.routeDataClass) { it.fragmentClass }
                                }
                            }
                        }
                    )
                }
                "UriRouterActivity2" -> {
                    val routes = listOf(
                        UriRoutePatch(
                            UriRouterFragment00::class,
                            UriRouterFragment00.Route::class,
                            KeyImpl(UriRouterFragment00.Route.ID)
                        ),
                        UriRoutePatch(
                            UriRouterFragment1::class,
                            UriRouterFragment1.Route::class,
                            KeyImpl(UriRouterFragment1.Route.ID)
                        ),
                        UriRoutePatch(
                            UriRouterFragment3::class,
                            UriRouterFragment3.Route::class,
                            KeyImpl(UriRouterFragment3.Route.ID)
                        )
                    )
                    UriRouterData(
                        routes,
                        FragmentRouter {
                            transitions {
                                register(FadeFragmentTransition())
                            }
                            routing {
                                routes.forEach {
                                    route(it.routeDataClass) { it.fragmentClass }
                                }
                            }
                        }
                    )
                }
                else -> {
                    throw IllegalArgumentException("Router $id is not implemented.")
                }
            }
        })
    }
}
