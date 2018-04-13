package com.gmail.jiangyang5157.kotlin_injector

import com.google.inject.Guice
import com.google.inject.Injector
import com.google.inject.Module
import java.util.*

/**
 * Created by Yang Jiang on April 13, 2018
 */
object AppInjector {

    private var injector: Injector? = null

    fun getInjector(): Injector? = injector

    fun create(vararg modules: Module) {
        injector = Guice.createInjector(Arrays.asList(*modules))
    }

    fun destroy() {
        injector = null
    }

}