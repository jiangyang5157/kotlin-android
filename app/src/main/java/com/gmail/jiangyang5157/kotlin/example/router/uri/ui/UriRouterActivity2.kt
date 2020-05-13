package com.gmail.jiangyang5157.kotlin.example.router.uri.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gmail.jiangyang5157.android.router.core.clear
import com.gmail.jiangyang5157.android.router.core.push
import com.gmail.jiangyang5157.android.router.fragment.FragmentRouter
import com.gmail.jiangyang5157.kotlin.R
import com.gmail.jiangyang5157.kotlin.example.router.RouterApi
import com.gmail.jiangyang5157.kotlin.example.router.usecase.*
import kotlinx.android.synthetic.main.activity_uri_router_exmaple.*

class UriRouterActivity2 : AppCompatActivity(), RouterFragmentActivitySupport<String> {

    @Suppress("UNCHECKED_CAST")
    override val router: FragmentRouter<RouteData<String>> by lazy {
        RouterApi["UriRouterActivity2"].router as FragmentRouter<RouteData<String>>
    }

    @Suppress("UNCHECKED_CAST")
    override val routeBuilder: RouteBuilder<String> by lazy {
        RouterApi["UriRouterActivity2"].routeBuilder as RouteBuilder<String>
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uri_router_exmaple)
        setSupportActionBar(toolbar)

        router.setup(savedInstanceState, R.id.content_router)
        if (null == savedInstanceState) {
            router {
                clear() push routeBuilder.build(
                    UriRoutePack("https://com.gmail.jiangyang5157/example/urirouter/page0?param1=Push by Activity&param2=testing")
                )
            }
        }
    }

    override fun onBackPressed() {
        router.popRetainRootImmediateOrFinish()
    }
}
