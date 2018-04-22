package com.gmail.jiangyang5157.kotlin_kit.math

/**
 * Created by Yang Jiang on June 26, 2017
 */
data class VectorD2(val x: Double, val y: Double) {

    constructor() : this(0.0, 0.0)

    constructor(double: Double) : this(double, double)

    operator fun unaryMinus(): VectorD2 = VectorD2(-x, -y)

    operator fun plus(other: VectorD2): VectorD2 = VectorD2(x + other.x, y + other.y)

    operator fun plus(int: Int): VectorD2 = VectorD2(x + int, y + int)

    operator fun plus(double: Double): VectorD2 = VectorD2(x + double, y + double)

    operator fun minus(other: VectorD2): VectorD2 = VectorD2(x - other.x, y - other.y)

    operator fun minus(int: Int): VectorD2 = VectorD2(x - int, y - int)

    operator fun minus(double: Double): VectorD2 = VectorD2(x - double, y - double)

    operator fun times(other: VectorD2): VectorD2 = VectorD2(x * other.x, y * other.y)

    operator fun times(int: Int): VectorD2 = VectorD2(x * int, y * int)

    operator fun times(double: Double): VectorD2 = VectorD2(x * double, y * double)

    operator fun div(other: VectorD2): VectorD2 = VectorD2(x / other.x, y / other.y)

    operator fun div(int: Int): VectorD2 = VectorD2(x / int, y / int)

    operator fun div(double: Double): VectorD2 = VectorD2(x / double, y / double)

    val length: Double
        get() = Math.sqrt(this.dot(this))

    val normalize: VectorD2
        get() = this / length

    fun dot(other: VectorD2): Double = x * other.x + y * other.y

    fun cross(other: VectorD2): Double = x * other.y + y * other.x

    fun alpha(): Double = Math.atan2(y, x)

    fun rotate(radian: Double): VectorD2 {
        val cosRadian = Math.cos(radian)
        val sinRadian = Math.sin(radian)
        return VectorD2(
                x * cosRadian - y * sinRadian,
                x * sinRadian + y * cosRadian
        )
    }

}
