package com.gmail.jiangyang5157.kotlin_kit.math

/**
 * Created by Yang Jiang on April 24, 2018
 */
data class VectorI2(override val x: Int, override val y: Int) : Vector2<Int> {

    constructor() : this(0, 0)
    constructor(int: Int) : this(int, int)

    operator fun unaryMinus(): VectorI2 = VectorI2(-x, -y)
    operator fun plus(other: VectorI2): VectorI2 = VectorI2(x + other.x, y + other.y)
    operator fun plus(int: Int): VectorI2 = VectorI2(x + int, y + int)
    operator fun plus(double: Double): VectorI2 = VectorI2((x + double).toInt(), (y + double).toInt())
    operator fun minus(other: VectorI2): VectorI2 = VectorI2(x - other.x, y - other.y)
    operator fun minus(int: Int): VectorI2 = VectorI2(x - int, y - int)
    operator fun minus(double: Double): VectorI2 = VectorI2((x - double).toInt(), (y - double).toInt())
    operator fun times(other: VectorI2): VectorI2 = VectorI2(x * other.x, y * other.y)
    operator fun times(int: Int): VectorI2 = VectorI2(x * int, y * int)
    operator fun times(double: Double): VectorI2 = VectorI2((x * double).toInt(), (y * double).toInt())
    operator fun div(other: VectorI2): VectorI2 = VectorI2(x / other.x, y / other.y)
    operator fun div(int: Int): VectorI2 = VectorI2(x / int, y / int)
    operator fun div(double: Double): VectorI2 = VectorI2((x / double).toInt(), (y / double).toInt())

    override val length: Double
        get() = Math.sqrt(this.dot(this))

    override val normalize: VectorI2
        get() = this / length

    override fun dot(other: Vector2<Int>): Double = (x * other.x + y * other.y).toDouble()
    override fun cross(other: Vector2<Int>): Double = (x * other.y + y * other.x).toDouble()

    override fun alpha(): Double = Math.atan2(y.toDouble(), x.toDouble())

    override fun rotate(radian: Double): VectorI2 {
        val cosRadian = Math.cos(radian)
        val sinRadian = Math.sin(radian)
        return VectorI2(
                (x * cosRadian - y * sinRadian).toInt(),
                (x * sinRadian + y * cosRadian).toInt()
        )
    }
}
