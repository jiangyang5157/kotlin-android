package com.gmail.jiangyang5157.kotlin.example.router

import com.gmail.jiangyang5157.android.router.fragment.FragmentRouter
import com.gmail.jiangyang5157.kotlin.example.router.transition.FadeFragmentTransition
import com.gmail.jiangyang5157.kotlin.example.router.uri.transition.UriRouterFragment1Transition
import com.gmail.jiangyang5157.kotlin.example.router.uri.transition.UriRouterFragment2Transition
import com.gmail.jiangyang5157.kotlin.example.router.uri.ui.*
import com.gmail.jiangyang5157.kotlin.example.router.usecase.UriRouteData
import com.gmail.jiangyang5157.kotlin_kit.model.Key
import java.lang.IllegalArgumentException
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

object RouterApi {

    private val routers = hashMapOf<String, FragmentRouter<UriRouteData>>()

    private val lock = ReentrantLock()

    operator fun get(id: String): FragmentRouter<UriRouteData> = lock.withLock {
        routers.getOrPut(id, {
            when (id) {
                "UriRouterActivity1" -> {
                    val asd = UriRouteData("")
                    FragmentRouter {
                        fragmentTransition {
                            register(FadeFragmentTransition())
                            register(UriRouterFragment1Transition())
                            register(UriRouterFragment2Transition())
                        }
                        fragmentMap {
                            map(Key("http://com.gmail.jiangyang5157/uri/page_info")) { UriRouterFragmentInfo::class }
                            map(Key("http://com.gmail.jiangyang5157/uri/page0")) { UriRouterFragment0::class }
                            map(Key("http://com.gmail.jiangyang5157/uri/page1")) { UriRouterFragment1::class }
                            map(Key("http://com.gmail.jiangyang5157/uri/page2")) { UriRouterFragment2::class }
                        }
                    }
                }
                "UriRouterActivity2" -> {
                    FragmentRouter {
                        fragmentTransition {
                            register(FadeFragmentTransition())
                        }
                        fragmentMap {
                            map(Key("http://com.gmail.jiangyang5157/uri/page_info")) { UriRouterFragmentInfo::class }
                            map(Key("http://com.gmail.jiangyang5157/uri/page0")) { UriRouterFragment00::class }
                            map(Key("http://com.gmail.jiangyang5157/uri/page1")) { UriRouterFragment1::class }
                            map(Key("http://com.gmail.jiangyang5157/uri/page2")) { UriRouterFragment2::class }
                        }
                    }
                }
                else -> {
                    throw IllegalArgumentException("Router $id is not implemented.")
                }
            }
        })
    }
}
