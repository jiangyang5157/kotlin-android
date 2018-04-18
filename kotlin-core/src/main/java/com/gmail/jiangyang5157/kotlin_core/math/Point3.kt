package com.gmail.jiangyang5157.kotlin_core.math

/**
 * Created by Yang Jiang on June 26, 2017
 */
data class Point3(val x: Double, val y: Double, val z: Double) {

    constructor() : this(0.0, 0.0, 0.0)
    constructor(d: Double) : this(d, d, d)

    operator fun unaryMinus(): Point3 = Point3(-x, -y, -z)

}