package com.gmail.jiangyang5157.kotlin_android_kit.ext

import android.os.Bundle
import android.support.v4.app.Fragment

/**
 * Created by Yang Jiang on July 16, 2017
 */

inline fun <reified T : Fragment> instance(args: Bundle? = null): Fragment = T::class.java.newInstance().apply { arguments = args }
