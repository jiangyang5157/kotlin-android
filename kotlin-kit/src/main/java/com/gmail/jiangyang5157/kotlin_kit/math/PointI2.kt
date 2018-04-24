package com.gmail.jiangyang5157.kotlin_kit.math

/**
 * Created by Yang Jiang on April 22, 2018
 */
data class PointI2(val x: Int, val y: Int) : Point {

    constructor() : this(0, 0)

    constructor(int: Int) : this(int, int)

    operator fun unaryMinus(): PointI2 = PointI2(-x, -y)

}