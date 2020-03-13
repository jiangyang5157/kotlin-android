package com.gmail.jiangyang5157.core.data

/**
 * Created by Yang Jiang on July 11, 2019
 *
 * A generic class that holds a value with its loading status.
 */
data class Resource<out T>(val status: Status, val data: T? = null, val message: String? = null) {

    companion object {

        fun <T> success(data: T?): Resource<T> {
            return Resource(
                Status.SUCCESS,
                data
            )
        }

        fun <T> error(data: T?, message: String?): Resource<T> {
            return Resource(
                Status.ERROR,
                data,
                message
            )
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(
                Status.LOADING,
                data
            )
        }
    }
}

/**
 * Status of a Resource that is provided to the UI.
 *
 *
 * These are usually created by the Repository classes where they return
 * `LiveData<Resource<T>>` to pass back the latest data to the UI with its fetch status.
 */
enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}
