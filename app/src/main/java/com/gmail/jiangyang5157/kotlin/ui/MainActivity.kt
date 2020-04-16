package com.gmail.jiangyang5157.kotlin.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.gmail.jiangyang5157.core.Injectable
import com.gmail.jiangyang5157.core.util.AppExecutor
import com.gmail.jiangyang5157.kotlin.R
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject
import kotlinx.android.synthetic.main.activity_main.*

open class MainActivity : AppCompatActivity(), HasSupportFragmentInjector, Injectable {

    @Inject
    protected lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector() = dispatchingAndroidInjector

    @Inject
    protected lateinit var appExecutor: AppExecutor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        Log.d("####", "appExecutor: $appExecutor")
    }
}
