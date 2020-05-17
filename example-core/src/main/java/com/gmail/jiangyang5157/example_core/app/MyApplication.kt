package com.gmail.jiangyang5157.example_core.app

import com.gmail.jiangyang5157.core.App
import com.gmail.jiangyang5157.core.util.AppExecutor
import com.gmail.jiangyang5157.feature_color.ColorModule
import javax.inject.Inject

class MyApplication : App() {

    @Inject
    lateinit var appExecutor: AppExecutor

    override fun inject() {
        DaggerAppComponent.builder().application(this)
            .module(ColorModule("Example of module with parameter"))
            .build().inject(this)
        assert(this::appExecutor.isInitialized)
    }
}
