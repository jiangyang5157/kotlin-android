package com.gmail.jiangyang5157.android.router.fragment

import androidx.fragment.app.Fragment
import com.gmail.jiangyang5157.kotlin_kit.model.Key
import kotlin.reflect.KClass

class EmptyFragmentMap : FragmentMap {

    override fun get(key: Key): KClass<out Fragment>? = null
}
