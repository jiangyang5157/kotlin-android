package com.gmail.jiangyang5157.example_core.example.router.keyroute.uri

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gmail.jiangyang5157.android.router.core.clear
import com.gmail.jiangyang5157.android.router.core.push
import com.gmail.jiangyang5157.android.router.fragment.FragmentRouter
import com.gmail.jiangyang5157.example_core.R
import com.gmail.jiangyang5157.example_core.example.router.Dependency
import com.gmail.jiangyang5157.example_core.example.router.RouterFragmentActivityHost
import kotlinx.android.synthetic.main.example_activity_router.*

class ExampleCustomStackStorageActivity : AppCompatActivity(),
    RouterFragmentActivityHost<UriRoute> {

    @Suppress("UNCHECKED_CAST")
    override val router: FragmentRouter<UriRoute> =
        Dependency.uriRouter["ExampleCustomStackStorageActivity"] as FragmentRouter<UriRoute>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.example_activity_router)
        setSupportActionBar(toolbar)

        router.setup(savedInstanceState, R.id.content_router)
        if (null == savedInstanceState) {
            router {
                clear() push UriRoute("http://example.router.uri/page1?param1=Push by ExampleCustomRouteStorageActivity")
            }
        }
    }

    override fun onBackPressed() {
        router.popRetainRootImmediateOrFinish()
    }
}
