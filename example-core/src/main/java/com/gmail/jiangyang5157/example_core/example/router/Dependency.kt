package com.gmail.jiangyang5157.example_core.example.router

import com.gmail.jiangyang5157.android.router.core.MultiRouter
import com.gmail.jiangyang5157.android.router.core.Route
import com.gmail.jiangyang5157.android.router.core.Router
import com.gmail.jiangyang5157.android.router.fragment.FragmentRouter
import com.gmail.jiangyang5157.android.router.fragment.setup.RouterFragment
import com.gmail.jiangyang5157.android.router.fragment.setup.RouterFragmentActivity
import com.gmail.jiangyang5157.android.router.fragment.setup.expectThisToBeAFragment
import com.gmail.jiangyang5157.example_core.example.router.fragmentroute.ExampleRoute
import com.gmail.jiangyang5157.example_core.example.router.keyroute.uri.*
import com.gmail.jiangyang5157.example_core.example.router.transition.DefaultFragmentTransition
import com.gmail.jiangyang5157.kotlin_kit.model.Key

/**
 * Implemented by FragmentActivity, initialize [router] value.
 */
interface RouterFragmentActivityHost<T : Route> : RouterFragmentActivity {
    val router: FragmentRouter<T>
}

/**
 * Implemented by Fragment, use [router] value from it's activity that expected to be a [RouterFragmentActivityHost]
 */
interface RouterFragmentGuest<T : Route> : RouterFragment {
    @Suppress("UNCHECKED_CAST")
    override val router: FragmentRouter<T>
        get() = (expectThisToBeAFragment().activity as RouterFragmentActivityHost<T>).router
}

/**
 * Mock dependency for providing [Router]
 */
object Dependency {

    val fragmentrouteRouter: MultiRouter<String, ExampleRoute> = MultiRouter {
        when (it) {
            "ExampleActivity" -> {
                FragmentRouter {
                    transition {
                        register(DefaultFragmentTransition())
                    }
                }
            }
            else -> {
                throw IllegalArgumentException("ExampleRoute $it is not implemented.")
            }
        }
    }

    val uriRouter: MultiRouter<String, UriRoute> = MultiRouter {
        when (it) {
            "ExampleActivity" -> {
                FragmentRouter {
                    transition {
                        register(DefaultFragmentTransition())
                    }
                    fragment {
                        map(Key("http://example.router.uri/page1")) { ExampleFragment1::class }
                        map(Key("http://example.router.uri/page2")) { ExampleFragment2::class }
                    }
                }
            }
            "ExampleCustomRouteStorageActivity" -> {
                FragmentRouter {
                    transition {
                        register(DefaultFragmentTransition())
                    }
                    fragment {
                        map(Key("http://example.router.uri/page1")) { ExampleFragment1::class }
                        map(Key("http://example.router.uri/page2")) { ExampleFragment2::class }
                    }
                    routeStorage(CustomRouteStorage())
                }
            }
            "ExampleCustomStackStorageActivity" -> {
                FragmentRouter {
                    transition {
                        register(DefaultFragmentTransition())
                    }
                    fragment {
                        map(Key("http://example.router.uri/page1")) { ExampleFragment1::class }
                        map(Key("http://example.router.uri/page2")) { ExampleFragment2::class }
                    }
                    stackStorage(CustomStackStorage())
                }
            }
            else -> {
                throw IllegalArgumentException("UriRoute $it is not implemented.")
            }
        }
    }
}
