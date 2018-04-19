package com.gmail.jiangyang5157.kotlin_kit.model

/**
 * Created by Yang Jiang on April 18, 2018
 */
interface Repository<T, in S> {

    fun add(item: T): Boolean

    fun add(items: Iterable<T>): Boolean

    fun find(spec: S): List<T>

    fun remove(spec: S): Boolean

    fun update(item: T, spec: S): Boolean

}

