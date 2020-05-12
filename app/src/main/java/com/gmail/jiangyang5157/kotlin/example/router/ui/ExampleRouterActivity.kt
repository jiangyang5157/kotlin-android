package com.gmail.jiangyang5157.kotlin.example.router.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gmail.jiangyang5157.kotlin.R
import com.gmail.jiangyang5157.kotlin.example.router.ui.any.AnyRouterActivity
import com.gmail.jiangyang5157.kotlin.example.router.ui.uri.UriRouterActivity
import kotlinx.android.synthetic.main.activity_router_example.*

class ExampleRouterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_router_example)
        setSupportActionBar(toolbar)

        btn_uri_router.setOnClickListener {
            startActivity(Intent(this, UriRouterActivity::class.java))
        }

        btn_any_router.setOnClickListener {
            startActivity(Intent(this, AnyRouterActivity::class.java))
        }
    }
}
