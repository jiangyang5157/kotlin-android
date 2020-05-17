package com.gmail.jiangyang5157.android.router.fragment.mapping

import androidx.fragment.app.Fragment
import com.gmail.jiangyang5157.android.router.core.Route
import kotlin.reflect.KClass

interface FragmentRoute : Route {

    val fragment: KClass<out Fragment>
}
