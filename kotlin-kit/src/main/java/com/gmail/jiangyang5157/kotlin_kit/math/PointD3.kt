package com.gmail.jiangyang5157.kotlin_kit.math

/**
 * Created by Yang Jiang on June 26, 2017
 */
data class PointD3(override val x: Double, override val y: Double, override val z: Double) : Point3<Double> {

    constructor() : this(0.0, 0.0, 0.0)
    constructor(double: Double) : this(double, double, double)

    operator fun unaryMinus(): PointD3 = PointD3(-x, -y, -z)
}