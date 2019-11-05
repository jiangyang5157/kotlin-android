package com.gmail.jiangyang5157.kotlin.model.color

import com.google.gson.annotations.SerializedName

/**
 * Created by Yang Jiang on July 11, 2019
 */
data class Colors(

    @field:SerializedName("color")
    val colors: List<Color>?
)
