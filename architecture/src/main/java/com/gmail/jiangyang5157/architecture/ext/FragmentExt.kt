package com.gmail.jiangyang5157.architecture.ext

import android.os.Bundle
import androidx.fragment.app.Fragment

/**
 * Created by Yang Jiang on May 06, 2019
 */
inline fun <reified T : Fragment> instance(args: Bundle? = null): Fragment {
    return T::class.java.newInstance().apply {
        arguments = args
    }
}
