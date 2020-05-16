package com.gmail.jiangyang5157.android.router.fragment

import androidx.fragment.app.Fragment
import com.gmail.jiangyang5157.kotlin_kit.model.Key
import kotlin.reflect.KClass

/**
 * # FragmentMap
 * Definition of which fragment should be displayed for a certain route
 */
interface FragmentMap {

    /**
     * @return
     * - The class of the fragment that should be displayed for the [key]
     * - `null` if this map does not contain any information about the given [key]
     */
    operator fun get(key: Key): KClass<out Fragment>?
}
