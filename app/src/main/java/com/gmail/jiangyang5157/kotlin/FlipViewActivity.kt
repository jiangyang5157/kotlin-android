package com.gmail.jiangyang5157.kotlin

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import com.gmail.jiangyang5157.kotlin_android_kit.widget.FlipView

/**
 * Created by Yang Jiang on September 01, 2017
 */
class FlipViewActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flipview)

        val flipView = findViewById(R.id.fv) as FlipView
        val frontImg = ImageView(this)
        val backImg = ImageView(this)
        frontImg.setImageDrawable(getDrawable(R.mipmap.ic_launcher))
        backImg.setImageDrawable(getDrawable(R.mipmap.ic_launcher_round))
        flipView.init(frontImg, backImg, FlipView.FlipDirection.BOTTOM_IN_TOP_OUT)

        findViewById(R.id.btn_applyflip).setOnClickListener { flipView.apply() }
        findViewById(R.id.btn_commitflip).setOnClickListener { flipView.commit() }
    }
}