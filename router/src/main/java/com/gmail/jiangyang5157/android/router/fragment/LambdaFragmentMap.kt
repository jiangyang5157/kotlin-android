package com.gmail.jiangyang5157.android.router.fragment

import androidx.fragment.app.Fragment
import com.gmail.jiangyang5157.kotlin_kit.model.Key
import kotlin.reflect.KClass

@PublishedApi
internal class LambdaFragmentMap<T : Key, R : T>(
    private val type: KClass<R>,
    private val lambda: R.() -> KClass<out Fragment>?
) : FragmentMap {

    override fun get(key: Key): KClass<out Fragment>? {
        return if (type.java.isInstance(key)) {
            @Suppress("UNCHECKED_CAST")
            lambda(key as R)
        } else {
            null
        }
    }
}
