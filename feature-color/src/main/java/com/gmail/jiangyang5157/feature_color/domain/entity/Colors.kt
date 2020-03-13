package com.gmail.jiangyang5157.feature_color.domain.entity

import com.google.gson.annotations.SerializedName

data class Colors(

    @field:SerializedName("color")
    val colors: List<Color>?
) {
    override fun toString(): String {
        return "Colors(\n" +
            "\tcolors=$colors, \n" +
            ")"
    }
}
