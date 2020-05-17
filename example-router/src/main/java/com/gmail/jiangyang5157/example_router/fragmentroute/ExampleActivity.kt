package com.gmail.jiangyang5157.example_router.fragmentroute

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gmail.jiangyang5157.android.router.core.clear
import com.gmail.jiangyang5157.android.router.core.push
import com.gmail.jiangyang5157.android.router.fragment.FragmentRouter
import com.gmail.jiangyang5157.example_router.R
import com.gmail.jiangyang5157.example_router.Dependency
import com.gmail.jiangyang5157.example_router.RouterFragmentActivityHost
import kotlinx.android.synthetic.main.example_activity_router.*

class ExampleActivity : AppCompatActivity(),
    RouterFragmentActivityHost<ExampleRoute> {

    @Suppress("UNCHECKED_CAST")
    override val router: FragmentRouter<ExampleRoute> =
        Dependency.fragmentrouteRouter["ExampleActivity"] as FragmentRouter<ExampleRoute>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.example_activity_router)
        setSupportActionBar(toolbar)

        router.setup(savedInstanceState, R.id.content_router)
        if (null == savedInstanceState) {
            router {
                clear() push ExampleRoute1("Push by ExampleActivity")
            }
        }
    }

    override fun onBackPressed() {
        router.popRetainRootImmediateOrFinish()
    }
}
