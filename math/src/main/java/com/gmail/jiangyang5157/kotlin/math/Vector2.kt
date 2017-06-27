package com.gmail.jiangyang5157.kotlin.math

/**
 * Created by Yang Jiang on June 26, 2017
 */
data class Vector2(val x: Double, val y: Double) {

    constructor() : this(0.0, 0.0)
    constructor(d: Double) : this(d, d)

    operator fun unaryMinus(): Vector2 = Vector2(-x, -y)

    operator fun plus(other: Vector2): Vector2 = Vector2(x + other.x, y + other.y)
    operator fun plus(int: Int): Vector2 = Vector2(x + int, y + int)
    operator fun plus(d: Double): Vector2 = Vector2(x + d, y + d)

    operator fun minus(other: Vector2): Vector2 = Vector2(x - other.x, y - other.y)
    operator fun minus(int: Int): Vector2 = Vector2(x - int, y - int)
    operator fun minus(double: Double): Vector2 = Vector2(x - double, y - double)

    operator fun times(other: Vector2): Vector2 = Vector2(x * other.x, y * other.y)
    operator fun times(int: Int): Vector2 = Vector2(x * int, y * int)
    operator fun times(double: Double): Vector2 = Vector2(x * double, y * double)

    operator fun div(other: Vector2): Vector2 = Vector2(x / other.x, y / other.y)
    operator fun div(int: Int): Vector2 = Vector2(x / int, y / int)
    operator fun div(double: Double): Vector2 = Vector2(x / double, y / double)

    fun dot(other: Vector2): Double = x * other.x + y * other.y

    fun cross(other: Vector2): Double = x * other.y + y * other.x

    fun length(): Double = Math.sqrt(this.dot(this))

    fun normalize(): Vector2 = this / length()

    fun alpha(): Double = Math.atan2(y, x)

    fun rotate(radian: Double): Vector2 {
        val cosRadian = Math.cos(radian)
        val sinRadian = Math.sin(radian)
        return Vector2(x * cosRadian - y * sinRadian, x * sinRadian + y * cosRadian)
    }

}
