package com.gmail.jiangyang5157.kotlin.repo.color

import android.util.Log
import androidx.lifecycle.LiveData
import com.gmail.jiangyang5157.architecture.AppExecutor
import com.gmail.jiangyang5157.architecture.net.ApiResponse
import com.gmail.jiangyang5157.architecture.net.ApiSuccessResponse
import com.gmail.jiangyang5157.architecture.repo.NetworkBoundResource
import com.gmail.jiangyang5157.architecture.repo.Resource
import com.gmail.jiangyang5157.architecture.util.AbsentLiveData
import com.gmail.jiangyang5157.kotlin.model.color.Color
import com.gmail.jiangyang5157.kotlin.model.color.Colors
import com.gmail.jiangyang5157.kotlin.net.color.ColorService
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Yang Jiang on July 12, 2019
 */
@Singleton
class ColorRepository @Inject constructor(
    private val appExecutor: AppExecutor,
    private val colorService: ColorService
) {

    fun loadColor(): LiveData<Resource<Color>> {
        return object : NetworkBoundResource<Color, Color>(appExecutor) {
            override fun loadFromDb(): LiveData<Color> {
                Log.d("####", "loadFromDb")
                return AbsentLiveData.create()
            }

            override fun shouldFetch(data: Color?): Boolean {
                return data == null
            }

            override fun createCall(): LiveData<ApiResponse<Color>> {
                return colorService.fetchColor()
            }

            override fun saveCallResult(item: Color) {
                Log.d("####", "saveCallResult $item")
            }

            override fun onFetchFailed(errorMessage: String?) {
                super.onFetchFailed(errorMessage)
            }

            override fun processResponse(response: ApiSuccessResponse<Color>): Color {
                return super.processResponse(response)
            }
        }.asLiveData()
    }

    fun loadColors(): LiveData<Resource<Colors>> {
        return object : NetworkBoundResource<Colors, Colors>(appExecutor) {

            override fun loadFromDb(): LiveData<Colors> {
                Log.d("####", "loadFromDb")
                return AbsentLiveData.create()
            }

            override fun shouldFetch(data: Colors?): Boolean {
                return data == null
            }

            override fun createCall(): LiveData<ApiResponse<Colors>> {
                Log.d("####", "createCall")
                return colorService.fetchColors()
            }

            override fun saveCallResult(item: Colors) {
                Log.d("####", "saveCallResult $item")
            }

            override fun onFetchFailed(errorMessage: String?) {
                super.onFetchFailed(errorMessage)
            }

            override fun processResponse(response: ApiSuccessResponse<Colors>): Colors {
                return super.processResponse(response)
            }
        }.asLiveData()
    }
}
