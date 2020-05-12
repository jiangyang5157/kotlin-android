package com.gmail.jiangyang5157.kotlin.example.router.ui.bundle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gmail.jiangyang5157.android.router.core.clear
import com.gmail.jiangyang5157.android.router.core.push
import com.gmail.jiangyang5157.android.router.fragment.RouterFragmentActivity
import com.gmail.jiangyang5157.kotlin.R
import com.gmail.jiangyang5157.kotlin.example.router.RouterApi
import kotlinx.android.synthetic.main.activity_bundle_router_example.*

class BundleRouterActivity : AppCompatActivity(), RouterFragmentActivity {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bundle_router_example)
        setSupportActionBar(toolbar)

        if (null == savedInstanceState) {
            RouterApi.setupBundleRouter()
        }
        RouterApi.bundleRouter.setup(savedInstanceState, R.id.content_router)
        if (null == savedInstanceState) {
            RouterApi.bundleRouter {
                clear() push RouterApi.bundleRoute(
                    BundleData(
                        "Page 0",
                        Bundle().apply {
                            putString("param1", "Push by Activity")
                            putString("param2", "testing")
                        })
                )
            }
        }
    }

    override fun onBackPressed() {
        RouterApi.bundleRouter.popRetainRootImmediateOrFinish()
    }
}
