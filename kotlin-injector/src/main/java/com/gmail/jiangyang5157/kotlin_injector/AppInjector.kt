package com.gmail.jiangyang5157.kotlin_injector

import com.google.inject.Guice
import com.google.inject.Injector
import com.google.inject.Module
import java.util.*

/**
 * Created by Yang Jiang on April 13, 2018
 */
object AppInjector {

    private var mInjector: Injector? = null

    // todo feature injectors

    fun getInjector(): Injector? {
        return mInjector
    }

    fun create(vararg modules: Module) {
        mInjector = Guice.createInjector(Arrays.asList(*modules))
    }

    fun destroy() {
        mInjector = null
    }

//    fun injectMembers(instance: Any) {
//        mInjector?.injectMembers(instance)
//    }
//
//    fun <T> getInstance(key: Key<T>): T? {
//        return mInjector?.getInstance(key)
//    }
//
//    fun <T> getInstance(type: Class<T>): T? {
//        return mInjector?.getInstance(type)
//    }

}