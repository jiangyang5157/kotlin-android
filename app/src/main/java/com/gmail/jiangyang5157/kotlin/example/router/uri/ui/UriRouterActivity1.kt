package com.gmail.jiangyang5157.kotlin.example.router.uri.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gmail.jiangyang5157.android.router.core.clear
import com.gmail.jiangyang5157.android.router.core.push
import com.gmail.jiangyang5157.android.router.fragment.FragmentRouter
import com.gmail.jiangyang5157.kotlin.R
import com.gmail.jiangyang5157.kotlin.example.router.RouterApi
import com.gmail.jiangyang5157.kotlin.example.router.RouterFragmentActivitySupport
import com.gmail.jiangyang5157.kotlin.example.router.uri.UriRoute
import kotlinx.android.synthetic.main.activity_uri_router_exmaple.*

class UriRouterActivity1 : AppCompatActivity(),
    RouterFragmentActivitySupport<UriRoute> {

    @Suppress("UNCHECKED_CAST")
    override val router: FragmentRouter<UriRoute> by lazy { RouterApi["UriRouterActivity1"] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uri_router_exmaple)
        setSupportActionBar(toolbar)

        router.setup(savedInstanceState, R.id.content_router)
        if (null == savedInstanceState) {
            router {
                clear() push UriRoute(
                    "http://com.gmail.jiangyang5157/uri/page0?param1=Push by UriRouterActivity1&param2=testing"
                )
            }
        }
    }

    override fun onBackPressed() {
        router.popRetainRootImmediateOrFinish()
    }
}
