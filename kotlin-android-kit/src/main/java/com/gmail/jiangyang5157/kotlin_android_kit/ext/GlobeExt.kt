package com.gmail.jiangyang5157.kotlin_android_kit.ext

import android.os.Build

/**
 * Created by Yang Jiang on April 24, 2018
 */

fun sdkValidate(sdkVersion: Int): Boolean = Build.VERSION.SDK_INT >= sdkVersion