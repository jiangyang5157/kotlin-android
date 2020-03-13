package com.gmail.jiangyang5157.feature_color.domain.repository

import androidx.lifecycle.LiveData
import com.gmail.jiangyang5157.architecture.data.Resource
import com.gmail.jiangyang5157.feature_color.domain.entity.Color
import com.gmail.jiangyang5157.feature_color.domain.entity.Colors

interface ColorRepository {

    fun getColor(): LiveData<Resource<Color>>

    fun setColor(color: Color?)

    fun getColors(): LiveData<Resource<Colors>>

    fun setColors(colors: Colors?)
}
