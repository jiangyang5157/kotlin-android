package com.gmail.jiangyang5157.kotlin_kit.math

/**
 * Created by Yang Jiang on April 22, 2018
 */
data class PointI3(val x: Int, val y: Int, val z: Int) {

    constructor() : this(0, 0, 0)

    constructor(int: Int) : this(int, int, int)

    operator fun unaryMinus(): PointI3 = PointI3(-x, -y, -z)

}