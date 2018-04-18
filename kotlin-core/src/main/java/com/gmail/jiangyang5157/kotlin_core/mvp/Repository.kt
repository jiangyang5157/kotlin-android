package com.gmail.jiangyang5157.kotlin_core.mvp

/**
 * Created by Yang Jiang on April 18, 2018
 */
interface Repository<T, in Spec> {

    fun add(item: T)

    fun addAll(items: Iterable<T>)

    fun remove(item: T)

    fun removeAll(spec: Spec)

    fun update(item: T)

    fun findAll(spec: Spec): List<T>

}

