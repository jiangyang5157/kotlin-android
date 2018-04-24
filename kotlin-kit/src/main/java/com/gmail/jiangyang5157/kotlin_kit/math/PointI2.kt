package com.gmail.jiangyang5157.kotlin_kit.math

/**
 * Created by Yang Jiang on April 22, 2018
 */
data class PointI2(override val x: Int, override val y: Int) : Point2<Int> {

    constructor() : this(0, 0)
    constructor(int: Int) : this(int, int)

    operator fun unaryMinus(): PointI2 = PointI2(-x, -y)
}