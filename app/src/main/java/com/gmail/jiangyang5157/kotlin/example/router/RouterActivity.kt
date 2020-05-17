package com.gmail.jiangyang5157.kotlin.example.router

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gmail.jiangyang5157.kotlin.R
import com.gmail.jiangyang5157.kotlin.example.router.uri.ui.UriRouterActivity1
import com.gmail.jiangyang5157.kotlin.example.router.uri.ui.UriRouterActivity2
import kotlinx.android.synthetic.main.activity_router_example.*

class RouterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_router_example)
        setSupportActionBar(toolbar)

        btn_uri_router_1.setOnClickListener {
            startActivity(Intent(this, UriRouterActivity1::class.java))
        }

        btn_uri_router_2.setOnClickListener {
            startActivity(Intent(this, UriRouterActivity2::class.java))
        }
    }
}
