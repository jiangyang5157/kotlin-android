package com.gmail.jiangyang5157.kotlin_kit.math

/**
 * Created by Yang Jiang on June 26, 2017
 */
data class PointD2(val x: Double, val y: Double) {

    constructor() : this(0.0, 0.0)

    constructor(double: Double) : this(double, double)

    operator fun unaryMinus(): PointD2 = PointD2(-x, -y)

}