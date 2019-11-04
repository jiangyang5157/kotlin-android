package com.gmail.jiangyang5157.architecture.repo

/**
 * Created by Yang Jiang on July 11, 2019
 *
 * A generic class that holds a value with its loading status.
 */
data class Resource<out T>(val status: Status, val data: T? = null, val message: String? = null) {

    companion object {

        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data)
        }

        fun <T> error(data: T?, message: String): Resource<T> {
            return Resource(Status.ERROR, data, message)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data)
        }
    }
}
