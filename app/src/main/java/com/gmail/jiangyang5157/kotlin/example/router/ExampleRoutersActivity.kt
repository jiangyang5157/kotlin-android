package com.gmail.jiangyang5157.kotlin.example.router

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gmail.jiangyang5157.kotlin.R
import com.gmail.jiangyang5157.kotlin.example.router.uri.ui.UriRouterActivity1
import com.gmail.jiangyang5157.kotlin.example.router.uri.ui.UriRouterActivity2
import kotlinx.android.synthetic.main.example_activity_routers.*

class ExampleRoutersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.example_activity_routers)
        setSupportActionBar(toolbar)

        btn_1.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    com.gmail.jiangyang5157.kotlin.example.router.fragmentroute.ExampleActivity::class.java
                )
            )
        }

        btn_2.setOnClickListener {
            startActivity(Intent(this, UriRouterActivity1::class.java))
        }

        btn_3.setOnClickListener {
            startActivity(Intent(this, UriRouterActivity2::class.java))
        }
    }
}
