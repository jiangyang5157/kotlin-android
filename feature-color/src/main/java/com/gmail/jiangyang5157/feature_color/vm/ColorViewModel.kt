package com.gmail.jiangyang5157.feature_color.vm

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.gmail.jiangyang5157.core.data.Resource
import com.gmail.jiangyang5157.feature_color.domain.entity.Color
import com.gmail.jiangyang5157.feature_color.domain.entity.Colors
import com.gmail.jiangyang5157.feature_color.domain.repo.ColorRepository

class ColorViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val colorRepository: ColorRepository
) : ViewModel() {

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
