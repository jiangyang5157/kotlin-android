package com.gmail.jiangyang5157.example_router

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        btn_1.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    com.gmail.jiangyang5157.example_router.fragmentroute.ExampleActivity::class.java
                )
            )
        }

        btn_2.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    com.gmail.jiangyang5157.example_router.keyroute.uri.ExampleActivity::class.java
                )
            )
        }

        btn_3.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    com.gmail.jiangyang5157.example_router.keyroute.uri.ExampleCustomRouteStorageActivity::class.java
                )
            )
        }

        btn_4.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    com.gmail.jiangyang5157.example_router.keyroute.uri.ExampleCustomStackStorageActivity::class.java
                )
            )
        }
    }
}
