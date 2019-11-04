package com.gmail.jiangyang5157.kotlin_android_kit.ext

import android.content.Context
import android.widget.Toast

/**
 * Created by Yang Jiang on April 24, 2018
 */
fun Any.toast(context: Context, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, this.toString(), duration).apply { show() }
}
