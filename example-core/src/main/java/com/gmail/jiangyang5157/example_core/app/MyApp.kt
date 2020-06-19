package com.gmail.jiangyang5157.example_core.app

import android.app.Application
import com.gmail.jiangyang5157.core.util.AppExecutor
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MyApp : Application() {

    @Inject
    lateinit var appExecutor: AppExecutor

    override fun onCreate() {
        super.onCreate()
        assert(this::appExecutor.isInitialized)
    }
}
