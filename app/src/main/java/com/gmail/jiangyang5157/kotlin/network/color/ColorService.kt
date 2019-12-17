package com.gmail.jiangyang5157.kotlin.network.color

import androidx.lifecycle.LiveData
import com.gmail.jiangyang5157.architecture.net.ApiResponse
import com.gmail.jiangyang5157.kotlin.model.color.Color
import com.gmail.jiangyang5157.kotlin.model.color.Colors
import retrofit2.http.GET

/**
 * Created by Yang Jiang on July 11, 2019
 */
interface ColorService {

    companion object {
        const val baseUrl = "https://www.google.com/"
    }

    @GET("color.json")
    fun fetchColor(): LiveData<ApiResponse<Color>>

    @GET("colors.json")
    fun fetchColors(): LiveData<ApiResponse<Colors>>
}
