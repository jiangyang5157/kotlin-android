package com.gmail.jiangyang5157.android.router.fragment

import androidx.fragment.app.Fragment
import com.gmail.jiangyang5157.android.router.core.Route
import kotlin.reflect.KClass

/**
 * # FragmentMap
 * Definition of which fragment should be displayed for a certain route
 */
interface FragmentMap<in T : Route> {

    /**
     * @return
     * - The class of the fragment that should be displayed for the [route]
     * - `null` if this map does not contain any information about the given [route]
     */
    operator fun get(route: T): KClass<out Fragment>?
}
