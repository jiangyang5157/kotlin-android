package com.gmail.jiangyang5157.architecture.network

import androidx.lifecycle.LiveData
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import retrofit2.CallAdapter
import retrofit2.Retrofit

/**
 * Created by Yang Jiang on July 11, 2019
 */
class LiveDataCallAdapterFactory : CallAdapter.Factory() {

    override fun get(
      returnType: Type,
      annotations: Array<Annotation>,
      retrofit: Retrofit
    ): LiveDataCallAdapter<*>? {
        if (getRawType(returnType) != LiveData::class.java) {
            return null
        }

        val observableType = getParameterUpperBound(0, returnType as ParameterizedType)
        val rawObservableType = getRawType(observableType)

        if (rawObservableType != ApiResponse::class.java) {
            throw IllegalArgumentException("type must be a ApiResponse")
        }

        if (observableType !is ParameterizedType) {
            throw IllegalArgumentException("resource must be a ParameterizedType")
        }

        val bodyType = getParameterUpperBound(0, observableType)
        return LiveDataCallAdapter<Any>(bodyType)
    }
}
