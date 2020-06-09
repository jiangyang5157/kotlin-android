package com.gmail.jiangyang5157.feature_color.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gmail.jiangyang5157.core.data.Resource
import com.gmail.jiangyang5157.feature_color.domain.entity.Color
import com.gmail.jiangyang5157.feature_color.domain.entity.Colors
import com.gmail.jiangyang5157.feature_color.domain.repository.ColorRepository

class ColorViewModel constructor(
    private val colorRepository: ColorRepository
) : ViewModel() {

    class Factory(private val colorRepository: ColorRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ColorViewModel(colorRepository) as T
        }
    }

    fun loadColor(forceFetch: Boolean = false): LiveData<Resource<Color>> {
        if (forceFetch) {
            colorRepository.setColor(null)
        }
        return colorRepository.getColor()
    }

    fun loadColors(forceFetch: Boolean = false): LiveData<Resource<Colors>> {
        if (forceFetch) {
            colorRepository.setColors(null)
        }
        return colorRepository.getColors()
    }
}
