package com.gmail.jiangyang5157.feature_color.domain.entity

import com.google.gson.annotations.SerializedName

data class Value(

    @field:SerializedName("rgba")
    val rgba: List<Int>?,

    @field:SerializedName("hex")
    val hex: String?
) {
    override fun toString(): String {
        return "Value(\n" +
            "\trgba=$rgba, \n" +
            "\thex=$hex, \n" +
            ")"
    }
}
