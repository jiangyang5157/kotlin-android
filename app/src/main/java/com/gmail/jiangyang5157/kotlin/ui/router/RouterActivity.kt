package com.gmail.jiangyang5157.kotlin.ui.router

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gmail.jiangyang5157.kotlin.R
import com.gmail.jiangyang5157.kotlin_android_kit.ext.instance
import com.gmail.jiangyang5157.kotlin_android_kit.ext.transact
import kotlinx.android.synthetic.main.activity_router.*

class RouterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_router)
        setSupportActionBar(toolbar)

        if (savedInstanceState == null) {
            supportFragmentManager.transact {
                replace(R.id.content_router, instance<RouterFragment>())
            }
        }
    }
}
