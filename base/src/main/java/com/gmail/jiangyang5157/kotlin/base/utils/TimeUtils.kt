package com.gmail.jiangyang5157.kotlin.base.utils

/**
 * Created by Yang Jiang on June 27, 2017
 */
object TimeUtils {

    val MILLI_IN_SECOND = 1000L
    val MICRO_IN_SECOND = MILLI_IN_SECOND * 1000L
    val NANO_IN_SECOND = MICRO_IN_SECOND * 1000L

    val MULTIPLICATOR_NANO_2_MICRO = 0.001
    val MULTIPLICATOR_NANO_2_MILLI = 0.000001
    val MULTIPLICATOR_NANO_2_SECOND = 0.000000001

    val MILLI_IN_MINUTE = 60 * MILLI_IN_SECOND
    val MILLI_IN_HOUR = 60 * MILLI_IN_MINUTE
    val MILLI_IN_DAY = 24 * MILLI_IN_HOUR

    fun nano2milli(time: Long): Double = time * MULTIPLICATOR_NANO_2_MILLI

}
