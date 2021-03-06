package com.gmail.jiangyang5157.android.router.fragment.mapping

import androidx.fragment.app.Fragment
import com.gmail.jiangyang5157.android.router.fragment.FragmentRouterDsl
import com.gmail.jiangyang5157.kotlin_kit.data.model.Key
import kotlin.reflect.KClass

@FragmentRouterDsl
class FragmentMapBuilder {

    private var fragmentMap: FragmentMap = EmptyFragmentMap()

    /**
     * ## Usage
     * `FragmentRouter { fragment { map(key) { fragmentClass } } }`
     */
    @FragmentRouterDsl
    fun map(key: Key, mapping: Key.() -> KClass<out Fragment>?) =
        add(LambdaFragmentMap(key, mapping))

    @FragmentRouterDsl
    fun add(fragmentMap: FragmentMap) {
        this.fragmentMap += fragmentMap
    }

    @FragmentRouterDsl
    fun clear() {
        this.fragmentMap = EmptyFragmentMap()
    }

    @FragmentRouterDsl
    operator fun FragmentMap.unaryPlus() = add(this)

    fun build(): FragmentMap = fragmentMap
}
