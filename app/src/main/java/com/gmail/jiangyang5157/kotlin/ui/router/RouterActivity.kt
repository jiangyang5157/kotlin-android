package com.gmail.jiangyang5157.kotlin.ui.router

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gmail.jiangyang5157.android.router.core.clear
import com.gmail.jiangyang5157.android.router.core.push
import com.gmail.jiangyang5157.android.router.fragment.RouterFragmentActivity
import com.gmail.jiangyang5157.kotlin.R
import kotlinx.android.synthetic.main.activity_router.*

class RouterActivity : AppCompatActivity(), RouterFragmentActivity {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_router)
        setSupportActionBar(toolbar)

        RouterDep.setupRouterForRouterActivity()
        RouterDep.routerActivityRouter.setup(savedInstanceState, R.id.content_router)
        RouterDep.routerActivityRouter {
            clear() push UriRouteRepo.build("https://com.gmail.jiangyang5157/RouterActivity/router0?info=From RouterActivity")
        }
    }

    override fun onBackPressed() {
        RouterDep.routerActivityRouter.popRetainRootImmediateOrFinish()
    }
}
