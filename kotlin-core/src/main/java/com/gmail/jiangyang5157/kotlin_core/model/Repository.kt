package com.gmail.jiangyang5157.kotlin_core.model

/**
 * Created by Yang Jiang on April 18, 2018
 */
interface Repository<T, in Spec> {

    fun add(item: T): Boolean

    fun addAll(items: Iterable<T>): Boolean

    fun remove(item: T): Boolean

    fun removeAll(spec: Spec): Boolean

    fun update(item: T): Boolean

    fun findAll(spec: Spec): List<T>

}

