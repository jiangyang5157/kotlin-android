package com.gmail.jiangyang5157.feature_color.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gmail.jiangyang5157.core.data.NetworkBoundResource
import com.gmail.jiangyang5157.core.data.Resource
import com.gmail.jiangyang5157.core.network.ApiResponse
import com.gmail.jiangyang5157.core.network.ApiSuccessResponse
import com.gmail.jiangyang5157.core.util.AppExecutor
import com.gmail.jiangyang5157.feature_color.domain.entity.Color
import com.gmail.jiangyang5157.feature_color.domain.entity.Colors
import com.gmail.jiangyang5157.feature_color.domain.repository.ColorRepository
import com.gmail.jiangyang5157.feature_color.service.ColorService
import javax.inject.Inject

class ColorRepositoryImpl @Inject constructor(
    private val appExecutor: AppExecutor,
    private val colorService: ColorService
) : ColorRepository {

    private var color: Color? = null

    private var colors: Colors? = null

    override fun getColor(): LiveData<Resource<Color>> {
        return object : NetworkBoundResource<Color, Color>(appExecutor) {
            override fun loadFromDb(): LiveData<Color> {
                return MutableLiveData<Color>().apply { value = color }
            }

            override fun shouldFetch(data: Color?): Boolean {
                return data == null
            }

            override fun createCall(): LiveData<ApiResponse<Color>> {
                return colorService.fetchColor()
            }

            override fun saveCallResult(item: Color) {
                color = item
            }

            override fun onFetchFailed(errorMessage: String?) {
                super.onFetchFailed(errorMessage)
            }

            override fun processResponse(response: ApiSuccessResponse<Color>): Color {
                return super.processResponse(response)
            }
        }.asLiveData()
    }

    override fun setColor(color: Color?) {
        this.color = color
    }

    override fun getColors(): LiveData<Resource<Colors>> {
        return object : NetworkBoundResource<Colors, Colors>(appExecutor) {

            override fun loadFromDb(): LiveData<Colors> {
                return MutableLiveData<Colors>().apply { value = colors }
            }

            override fun shouldFetch(data: Colors?): Boolean {
                return data == null
            }

            override fun createCall(): LiveData<ApiResponse<Colors>> {
                return colorService.fetchColors()
            }

            override fun saveCallResult(item: Colors) {
                colors = item
            }

            override fun onFetchFailed(errorMessage: String?) {
                super.onFetchFailed(errorMessage)
            }

            override fun processResponse(response: ApiSuccessResponse<Colors>): Colors {
                return super.processResponse(response)
            }
        }.asLiveData()
    }

    override fun setColors(colors: Colors?) {
        this.colors = colors
    }
}
