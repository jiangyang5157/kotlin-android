package com.gmail.jiangyang5157.android.router.fragment.mapping

import androidx.fragment.app.Fragment
import com.gmail.jiangyang5157.kotlin_kit.data.model.Key
import kotlin.reflect.KClass

class EmptyFragmentMap : FragmentMap {

    override fun get(key: Key): KClass<out Fragment>? = null
}
