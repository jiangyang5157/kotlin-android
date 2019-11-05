package com.gmail.jiangyang5157.kotlin.model.color

import com.google.gson.annotations.SerializedName

/**
 * Created by Yang Jiang on July 11, 2019
 */
data class Code(

    @field:SerializedName("rgba")
    val rgba: List<Int>?,

    @field:SerializedName("hex")
    val hex: String?
)
