package com.gmail.jiangyang5157.kotlin.app

import com.gmail.jiangyang5157.core.App
import com.gmail.jiangyang5157.feature_color.ColorModule

class MyApplication : App() {

    override fun inject() {
        DaggerAppComponent.builder()
            .application(this)
            .module(ColorModule("Example of module with parameter"))
            .build().inject(this)
    }
}
