package com.gmail.jiangyang5157.core

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
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
        inject()
        registerActivityLifecycleCallbacks(activityLifecycleCallbacks)
    }

    private val activityLifecycleCallbacks = object : ActivityLifecycleCallbacks {
        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            if (activity is HasAndroidInjector) {
                AndroidInjection.inject(activity)
            }

            if (activity is FragmentActivity) {
                activity.supportFragmentManager.registerFragmentLifecycleCallbacks(
                    object : FragmentManager.FragmentLifecycleCallbacks() {
                        override fun onFragmentCreated(
                            fragmentManager: FragmentManager,
                            fragment: Fragment,
                            savedInstanceState: Bundle?
                        ) {
                            if (fragment is Injectable) {
                                AndroidSupportInjection.inject(fragment)
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
