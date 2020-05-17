package com.gmail.jiangyang5157.android.router.fragment.mapping

import androidx.fragment.app.Fragment
import com.gmail.jiangyang5157.android.router.core.KeyRoute
import com.gmail.jiangyang5157.android.router.fragment.FragmentElementImpl
import com.gmail.jiangyang5157.android.router.fragment.FragmentRoute
import com.gmail.jiangyang5157.kotlin_kit.model.Key
import kotlin.reflect.KClass

/**
 * # FragmentMap
 * Definition of which fragment should be displayed for a certain route
 *
 * In order to find fragment mapping, the route should be either [FragmentRoute] or [KeyRoute].
 * @see FragmentElementImpl
 */
interface FragmentMap {

    /**
     * @return
     * - The class of the fragment that should be displayed for the [key]
     * - `null` if this map does not contain any information about the given [key]
     */
    operator fun get(key: Key): KClass<out Fragment>?
}
