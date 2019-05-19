package com.gmail.jiangyang5157.kotlin_android_kit.ext

import android.os.Bundle
import androidx.fragment.app.Fragment

/**
 * Created by Yang Jiang on July 16, 2017
 */

inline fun <reified T : androidx.fragment.app.Fragment> instance(args: Bundle? = null): androidx.fragment.app.Fragment = T::class.java.newInstance().apply { arguments = args }
