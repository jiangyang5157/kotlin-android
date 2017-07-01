package com.gmail.jiangyang5157.kotlin_core.utils

/**
 * Created by Yang Jiang on June 27, 2017
 */
object TimeUtils {

    val MILLI_IN_SECOND: Long = 1000L
    val MICRO_IN_SECOND: Long = MILLI_IN_SECOND * 1000L
    val NANO_IN_SECOND: Long = MICRO_IN_SECOND * 1000L

    val MULTIPLICATOR_NANO_2_MICRO: Double = 0.001
    val MULTIPLICATOR_NANO_2_MILLI: Double = 0.000001
    val MULTIPLICATOR_NANO_2_SECOND: Double = 0.000000001

    val MILLI_IN_MINUTE: Long = 60 * MILLI_IN_SECOND
    val MILLI_IN_HOUR: Long = 60 * MILLI_IN_MINUTE
    val MILLI_IN_DAY: Long = 24 * MILLI_IN_HOUR

    fun nano2milli(time: Long): Double = time * MULTIPLICATOR_NANO_2_MILLI

}
