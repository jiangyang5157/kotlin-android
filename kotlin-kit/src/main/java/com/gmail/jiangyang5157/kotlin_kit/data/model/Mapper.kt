package com.gmail.jiangyang5157.kotlin_kit.data.model

/**
 * Created by Yang Jiang on April 18, 2018
 */
interface Mapper<in From, out To> {

    fun map(from: From): To
}
