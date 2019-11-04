package com.gmail.jiangyang5157.kotlin.model.color

import com.google.gson.annotations.SerializedName

/**
 * Created by Yang Jiang on July 11, 2019
 */
data class Color(

        @field:SerializedName("color")
        val color: String?,

        @field:SerializedName("category")
        val category: String?,

        @field:SerializedName("type")
        val type: String?,

        @field:SerializedName("code")
        val code: Code?
)
