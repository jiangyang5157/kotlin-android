package com.gmail.jiangyang5157.feature_color.domain.entity

import com.google.gson.annotations.SerializedName

data class Color(

    @field:SerializedName("color")
    val color: String?,

    @field:SerializedName("category")
    val category: String?,

    @field:SerializedName("type")
    val type: String?,

    @field:SerializedName("value")
    val value: Value?
) {
    override fun toString(): String {
        return "Color(\n" +
            "\tcolor=$color, \n" +
            "\tcategory=$category, \n" +
            "\ttype=$type, \n" +
            "\tvalue=$value, \n" +
            ")"
    }
}
