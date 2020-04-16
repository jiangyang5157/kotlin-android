package com.gmail.jiangyang5157.kotlin.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.gmail.jiangyang5157.core.util.AppExecutor
import com.gmail.jiangyang5157.kotlin.R
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject
import kotlinx.android.synthetic.main.activity_main.*

open class MainActivity : AppCompatActivity(), HasAndroidInjector {

    @Inject
    protected lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    @Inject
    protected lateinit var appExecutor: AppExecutor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        Log.d("####", "appExecutor: $appExecutor")
    }
}
