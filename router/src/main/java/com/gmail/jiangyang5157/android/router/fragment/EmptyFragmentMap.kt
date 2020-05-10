package com.gmail.jiangyang5157.android.router.fragment

import androidx.fragment.app.Fragment
import com.gmail.jiangyang5157.android.router.core.Route
import kotlin.reflect.KClass

class EmptyFragmentMap<T : Route> : FragmentMap<T> {

    override fun get(route: T): KClass<out Fragment>? = null
}
