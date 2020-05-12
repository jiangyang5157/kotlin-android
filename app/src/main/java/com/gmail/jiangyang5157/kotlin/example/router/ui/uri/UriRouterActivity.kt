package com.gmail.jiangyang5157.kotlin.example.router.ui.uri

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gmail.jiangyang5157.android.router.core.clear
import com.gmail.jiangyang5157.android.router.core.push
import com.gmail.jiangyang5157.android.router.fragment.RouterFragmentActivity
import com.gmail.jiangyang5157.kotlin.R
import com.gmail.jiangyang5157.kotlin.example.router.RouterApi
import kotlinx.android.synthetic.main.activity_uri_router_exmaple.*

class UriRouterActivity : AppCompatActivity(), RouterFragmentActivity {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uri_router_exmaple)
        setSupportActionBar(toolbar)
        toolbar.title = ""

        if (null == savedInstanceState) {
            RouterApi.setupRouterForRouterActivity()
        }
        RouterApi.router.setup(savedInstanceState, R.id.content_router)
        if (null == savedInstanceState) {
            RouterApi.router {
                clear() push RouterApi.route(
                    "https://com.gmail.jiangyang5157/example/urirouter/page0?param1=From Home Page&param2=additional info"
                )
            }
        }
    }

    override fun onBackPressed() {
        RouterApi.router.popRetainRootImmediateOrFinish()
    }
}
