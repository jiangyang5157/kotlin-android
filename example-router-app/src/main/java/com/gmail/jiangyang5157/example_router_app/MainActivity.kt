package com.gmail.jiangyang5157.example_router_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.gmail.jiangyang5157.android.router.fragment.setup.RouterFragmentActivity
import com.gmail.jiangyang5157.example_router_app.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), RouterFragmentActivity {

    private val router = Dependency.router

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        viewModel = ViewModelProviders.of(this)[MainViewModel::class.java]
        if (savedInstanceState == null) {
            viewModel.checkIfLoggedIn()
        }
        router.setup(savedInstanceState, R.id.content_router)
    }

    override fun onBackPressed() {
        router.popRetainRootImmediateOrFinish()
    }
}
