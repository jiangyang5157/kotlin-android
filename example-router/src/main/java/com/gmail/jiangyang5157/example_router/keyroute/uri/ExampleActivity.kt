package com.gmail.jiangyang5157.example_router.keyroute.uri

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gmail.jiangyang5157.android.router.core.clear
import com.gmail.jiangyang5157.android.router.core.push
import com.gmail.jiangyang5157.android.router.fragment.FragmentRouter
import com.gmail.jiangyang5157.example_router.Dependency
import com.gmail.jiangyang5157.example_router.R
import com.gmail.jiangyang5157.example_router.RouterFragmentActivityHost
import kotlinx.android.synthetic.main.example_activity_router.*

class ExampleActivity : AppCompatActivity(),
    RouterFragmentActivityHost<UriRoute> {

    @Suppress("UNCHECKED_CAST")
    override val router: FragmentRouter<UriRoute> =
        Dependency.uriRouter["ExampleActivity"] as FragmentRouter<UriRoute>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.example_activity_router)
        setSupportActionBar(toolbar)

        if (null == savedInstanceState) {
            router {
                clear() push UriRoute("http://example.router.uri/page1?param1=Push by ExampleActivity")
            }
        }
        router.setup(savedInstanceState, R.id.content_router)
    }

    override fun onBackPressed() {
        router.popRetainRootImmediateOrFinish()
    }
}
