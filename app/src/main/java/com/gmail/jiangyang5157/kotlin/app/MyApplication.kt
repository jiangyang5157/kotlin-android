package com.gmail.jiangyang5157.kotlin.app

import com.gmail.jiangyang5157.architecture.App
import com.gmail.jiangyang5157.kotlin.di.DaggerAppComponent

class MyApplication : App() {

    override fun inject() {
        DaggerAppComponent.builder().application(this).build().inject(this)
    }
}
