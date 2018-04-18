package com.gmail.jiangyang5157.kotlin_core.model

/**
 * Created by Yang Jiang on April 18, 2018
 */
interface Repository<T, in Specification> {

    fun add(item: T): Boolean

    fun add(items: Iterable<T>): Boolean

    fun find(spec: Specification): List<T>

    fun remove(spec: Specification): Boolean

    fun update(item: T, spec: Specification): Boolean

}

