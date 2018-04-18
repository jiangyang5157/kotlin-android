package com.gmail.jiangyang5157.kotlin_core.math

/**
 * Created by Yang Jiang on June 26, 2017
 */
data class Point2(val x: Double, val y: Double) {

    constructor() : this(0.0, 0.0)
    constructor(d: Double) : this(d, d)

    operator fun unaryMinus(): Point2 = Point2(-x, -y)

}