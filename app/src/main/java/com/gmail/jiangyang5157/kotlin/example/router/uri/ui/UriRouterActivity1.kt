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

class UriRouterActivity1 : AppCompatActivity(), RouterFragmentActivitySupport<UriRouteData> {

    @Suppress("UNCHECKED_CAST")
    override val router: FragmentRouter<UriRouteData> by lazy {
        RouterApi["UriRouterActivity1"] as FragmentRouter<UriRouteData>
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uri_router_exmaple)
        setSupportActionBar(toolbar)

        router.setup(savedInstanceState, R.id.content_router)
        if (null == savedInstanceState) {
            router {
                clear() push UriRouteElement(
                    "https://com.gmail.jiangyang5157/example/urirouter/page0?param1=Push by UriRouterActivity1&param2=testing"
                )
            }
        }
    }

    override fun onBackPressed() {
        router.popRetainRootImmediateOrFinish()
    }
}
