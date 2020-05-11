package com.gmail.jiangyang5157.kotlin.ui.router

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gmail.jiangyang5157.android.router.core.clear
import com.gmail.jiangyang5157.android.router.core.push
import com.gmail.jiangyang5157.android.router.fragment.FragmentRouter
import com.gmail.jiangyang5157.android.router.fragment.RouterFragmentActivity
import com.gmail.jiangyang5157.kotlin.R
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.activity_router.*
import javax.inject.Inject

class RouterActivity : AppCompatActivity(), HasAndroidInjector, RouterFragmentActivity {

    @Inject
    protected lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    @Inject
    lateinit var router: FragmentRouter<RouterActivityRoute>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_router)
        setSupportActionBar(toolbar)
        router.setup(savedInstanceState, R.id.content_router)
        router { clear() push Route0(info = "From RouterActivity") }
    }

    override fun onBackPressed() {
        router.popRetainRootImmediateOrFinish()
    }
}
