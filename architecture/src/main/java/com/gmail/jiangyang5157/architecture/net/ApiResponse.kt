package com.gmail.jiangyang5157.architecture.net

import retrofit2.Response
import java.net.HttpURLConnection

/**
 * Created by Yang Jiang on July 11, 2019
 */
sealed class ApiResponse<T> {

    companion object {

        fun <T> create(error: Throwable): ApiResponse<T> {
            return ApiErrorResponse(error.message ?: "unknown error")
        }

        fun <T> create(response: Response<T>): ApiResponse<T> {
            return if (response.isSuccessful) {
                val responseBody = response.body()

                if (response.code() == HttpURLConnection.HTTP_NO_CONTENT || responseBody == null) {
                    ApiEmptyResponse()
                } else {
                    ApiSuccessResponse(responseBody)
                }
            } else {
                val errorBody = response.errorBody()?.string()
                val errorMessage = if (errorBody.isNullOrEmpty()) {
                    response.message()
                } else {
                    errorBody
                }
                ApiErrorResponse(errorMessage)
            }
        }
    }
}

data class ApiErrorResponse<T>(val errorMessage: String) : ApiResponse<T>()

data class ApiSuccessResponse<T>(val responseBody: T) : ApiResponse<T>()

class ApiEmptyResponse<T> : ApiResponse<T>()
