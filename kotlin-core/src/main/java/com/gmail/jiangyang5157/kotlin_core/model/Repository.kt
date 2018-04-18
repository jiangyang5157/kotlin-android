package com.gmail.jiangyang5157.kotlin_core.model

/**
 * Created by Yang Jiang on April 18, 2018
 */
interface Repository<T, in Spec> {

    fun add(item: T): Boolean

    fun add(items: Iterable<T>): Boolean

    fun remove(spec: Spec): Boolean

    fun update(spec: Spec): Boolean

    fun find(spec: Spec): List<T>

}

