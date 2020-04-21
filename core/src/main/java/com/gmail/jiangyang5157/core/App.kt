package com.gmail.jiangyang5157.core

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.os.Debug
import android.util.Log
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by Yang Jiang on July 11, 2019
 */
abstract class App : Application(), HasAndroidInjector {

    @MainThread
    abstract fun inject()

    @Inject
    protected lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onCreate() {
        super.onCreate()
        measure("####") { inject() }
        registerActivityLifecycleCallbacks(activityLifecycleCallbacks)
    }

    fun measure(tag: String, block: () -> Unit) {
        val startTime = System.nanoTime()
        // /storage/emulated/0/Android/data/com.fiserv.touchbanking/files
        Debug.startMethodTracing("SplashTrace")
        block()
        Debug.stopMethodTracing()
        val endTime = System.nanoTime()
        Log.d(tag, "measure = ${TimeUnit.MILLISECONDS.convert(endTime - startTime, TimeUnit.NANOSECONDS)} ms")
    }

    private val activityLifecycleCallbacks = object : ActivityLifecycleCallbacks {
        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            if (activity is HasAndroidInjector) {
                AndroidInjection.inject(activity)
            }

            if (activity is FragmentActivity) {
                activity.supportFragmentManager.registerFragmentLifecycleCallbacks(
                    object : FragmentManager.FragmentLifecycleCallbacks() {
                        override fun onFragmentPreAttached(
                            fm: FragmentManager,
                            f: Fragment,
                            context: Context
                        ) {
                            if (f is Injectable) {
                                AndroidSupportInjection.inject(f)
                            }
                        }
                    }, true
                )
            }
        }

        override fun onActivityStarted(activity: Activity) {}
        override fun onActivityResumed(activity: Activity) {}
        override fun onActivityPaused(activity: Activity) {}
        override fun onActivityStopped(activity: Activity) {}
        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle?) {}
        override fun onActivityDestroyed(activity: Activity) {}
    }
}
