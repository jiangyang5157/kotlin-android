package com.gmail.jiangyang5157.android.router.fragment.mapping

import androidx.fragment.app.Fragment
import com.gmail.jiangyang5157.kotlin_kit.data.model.Key
import kotlin.reflect.KClass

internal class CompositeFragmentMap(
    private val first: FragmentMap,
    private val second: FragmentMap
) : FragmentMap {

    override fun get(key: Key): KClass<out Fragment>? = first[key] ?: second[key]
}

operator fun FragmentMap.plus(other: FragmentMap): FragmentMap =
    CompositeFragmentMap(
        this,
        other
    )
