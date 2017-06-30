package com.gmail.jiangyang5157.kotlin.core.data.structure.graph

/**
 * Created by Yang Jiang on June 28, 2017
 */
open class Node<T>(val id: T) {

    override fun toString(): String {
        return "Node(id=$id)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Node<*>

        if (id != other.id) return false
        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

}
