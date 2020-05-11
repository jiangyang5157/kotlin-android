package com.gmail.jiangyang5157.android.router.fragment

import androidx.fragment.app.Fragment
import com.gmail.jiangyang5157.android.router.core.Route
import kotlin.reflect.KClass

@FragmentRouterDsl
class FragmentMapBuilder<T : Route> {

    private var fragmentMap: FragmentMap<T> = EmptyFragmentMap()

    @FragmentRouterDsl
    inline fun <reified R : T> route(noinline mapping: R.() -> KClass<out Fragment>?) {
        add(LambdaFragmentMap(R::class, mapping))
    }

    @FragmentRouterDsl
    fun add(fragmentMap: FragmentMap<T>) {
        this.fragmentMap += fragmentMap
    }

    @FragmentRouterDsl
    fun clear() {
        this.fragmentMap = EmptyFragmentMap()
    }

    @FragmentRouterDsl
    operator fun FragmentMap<T>.unaryPlus() {
        add(this)
    }

    internal fun build(): FragmentMap<T> {
        return fragmentMap
    }
}
