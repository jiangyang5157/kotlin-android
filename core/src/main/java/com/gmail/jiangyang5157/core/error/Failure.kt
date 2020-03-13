package com.gmail.jiangyang5157.core.error

/**
 * Created by Yang Jiang on December 17, 2019
 */
abstract class Failure(val properties: List<Any>) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Failure

        if (properties != other.properties) return false

        return true
    }

    override fun hashCode(): Int {
        return properties.hashCode()
    }
}

open class ServerFailure(val message: String = "") : Failure(listOf(message))

open class CacheFailure(val message: String = "") : Failure(listOf(message))
