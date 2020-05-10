package com.gmail.jiangyang5157.kotlin.app

import android.util.Log
import com.gmail.jiangyang5157.android.router.fragment.FragmentRouter
import com.gmail.jiangyang5157.core.App
import com.gmail.jiangyang5157.core.util.AppExecutor
import com.gmail.jiangyang5157.feature_color.ColorModule
import com.gmail.jiangyang5157.kotlin.AppModule
import com.gmail.jiangyang5157.kotlin.ui.router.*
import javax.inject.Inject

class MyApplication : App() {

    @Inject
    lateinit var appExecutor: AppExecutor

    override fun inject() {
        DaggerAppComponent.builder().application(this)
            .module(AppModule())
            .module(ColorModule("Example of module with parameter"))
            .build().inject(this)
        // Example of application injection
        Log.d("####", "$appExecutor")
    }

    override fun onCreate() {
        super.onCreate()

        Dependencies.router = FragmentRouter {
            transitions {
                register(RFTransition())
            }
            routing {
                route<Route0> { RouterFragment0::class }
                route<Route1> { RouterFragment1::class }
                route<Route2> { RouterFragment2::class }
                route<Route3> { RouterFragment3::class }
            }
        }
    }
}
