package com.gmail.jiangyang5157.kotlin_android_core.ext

import android.app.Fragment
import android.os.Bundle

/**
 * Created by Yang Jiang on July 16, 2017
 */
inline fun <reified T : Fragment> instance(args: Bundle? = null): Fragment
        = T::class.java.newInstance().apply {
    arguments = args
}
