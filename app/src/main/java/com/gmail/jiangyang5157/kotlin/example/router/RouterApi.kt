package com.gmail.jiangyang5157.kotlin.example.router

import com.gmail.jiangyang5157.android.router.fragment.FragmentRouter
import com.gmail.jiangyang5157.kotlin.example.router.transition.FadeFragmentTransition
import com.gmail.jiangyang5157.kotlin.example.router.uri.transition.UriRouterFragment1Transition
import com.gmail.jiangyang5157.kotlin.example.router.uri.transition.UriRouterFragment2Transition
import com.gmail.jiangyang5157.kotlin.example.router.uri.ui.*
import com.gmail.jiangyang5157.kotlin.example.router.usecase.RouterData
import com.gmail.jiangyang5157.kotlin.example.router.usecase.UriRoutePatch
import com.gmail.jiangyang5157.kotlin.example.router.usecase.UriRouterData
import com.gmail.jiangyang5157.kotlin_kit.model.Key
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
                            Key(UriRouterFragment0.Route.ID)
                        ),
                        UriRoutePatch(
                            UriRouterFragment1::class,
                            UriRouterFragment1.Route::class,
                            Key(UriRouterFragment1.Route.ID)
                        ),
                        UriRoutePatch(
                            UriRouterFragment2::class,
                            UriRouterFragment2.Route::class,
                            Key(UriRouterFragment2.Route.ID)
                        ),
                        UriRoutePatch(
                            UriRouterFragment3::class,
                            UriRouterFragment3.Route::class,
                            Key(UriRouterFragment3.Route.ID)
                        )
                    )
                    UriRouterData(
                        routes,
                        FragmentRouter {
                            transitions {
                                register(FadeFragmentTransition())
                                register(UriRouterFragment1Transition())
                                register(UriRouterFragment2Transition())
                            }
                        }
                    )
                }
                "UriRouterActivity2" -> {
                    val routes = listOf(
                        UriRoutePatch(
                            UriRouterFragment00::class,
                            UriRouterFragment00.Route::class,
                            Key(UriRouterFragment00.Route.ID)
                        ),
                        UriRoutePatch(
                            UriRouterFragment1::class,
                            UriRouterFragment1.Route::class,
                            Key(UriRouterFragment1.Route.ID)
                        ),
                        UriRoutePatch(
                            UriRouterFragment2::class,
                            UriRouterFragment2.Route::class,
                            Key(UriRouterFragment2.Route.ID)
                        ),
                        UriRoutePatch(
                            UriRouterFragment3::class,
                            UriRouterFragment3.Route::class,
                            Key(UriRouterFragment3.Route.ID)
                        )
                    )
                    UriRouterData(
                        routes,
                        FragmentRouter {
                            transitions {
                                register(FadeFragmentTransition())
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
