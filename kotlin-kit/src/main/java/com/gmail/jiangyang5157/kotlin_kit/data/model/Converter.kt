package com.gmail.jiangyang5157.kotlin_kit.data.model

/**
 * Created by Yang Jiang on July 29, 2018
 */
interface Converter<in From, in To> {

    fun convert(src: From, dst: To)
}
