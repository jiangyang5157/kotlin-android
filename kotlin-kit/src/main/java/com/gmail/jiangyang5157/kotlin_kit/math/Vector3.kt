package com.gmail.jiangyang5157.kotlin_kit.math

/**
 * Created by Yang Jiang on June 27, 2017
 */
data class Vector3(val x: Double, val y: Double, val z: Double) {

    constructor() : this(0.0, 0.0, 0.0)
    constructor(d: Double) : this(d, d, d)

    operator fun unaryMinus(): Vector3 = Vector3(-x, -y, -z)

    operator fun plus(other: Vector3): Vector3 = Vector3(x + other.x, y + other.y, z + other.z)
    operator fun plus(int: Int): Vector3 = Vector3(x + int, y + int, z + int)
    operator fun plus(double: Double): Vector3 = Vector3(x + double, y + double, z + double)

    operator fun minus(other: Vector3): Vector3 = Vector3(x - other.x, y - other.y, z - other.z)
    operator fun minus(int: Int): Vector3 = Vector3(x - int, y - int, z - int)
    operator fun minus(double: Double): Vector3 = Vector3(x - double, y - double, z - double)

    operator fun times(other: Vector3): Vector3 = Vector3(x * other.x, y * other.y, z * other.z)
    operator fun times(int: Int): Vector3 = Vector3(x * int, y * int, z * int)
    operator fun times(double: Double): Vector3 = Vector3(x * double, y * double, z * double)

    operator fun div(other: Vector3): Vector3 = Vector3(x / other.x, y / other.y, z / other.z)
    operator fun div(int: Int): Vector3 = Vector3(x / int, y / int, z / int)
    operator fun div(double: Double): Vector3 = Vector3(x / double, y / double, z / double)

    val length: Double
        get() = Math.sqrt(this.dot(this))

    val normalize: Vector3
        get() = this / length

    fun dot(other: Vector3): Double = x * other.x + y * other.y + z * other.z

    fun cross(other: Vector3): Vector3 = Vector3(
            y * other.z - z * other.y,
            z * other.x - x * other.z,
            x * other.y - y * other.x
    )

    fun alpha(): Double = Math.atan2(y, x)

    fun delta(): Double = Math.asin(z / length)

    fun radian(other: Vector3): Double {
        var r = dot(other) * (1.0 / (length * other.length))
        if (r < -1.0) {
            r = -1.0
        } else if (r > 1.0) {
            r = 1.0
        }
        return Math.acos(r)
    }

    fun rotateXaxis(radian: Double): Vector3 {
        val sin = Math.sin(radian)
        val cos = Math.cos(radian)
        return Vector3(
                x,
                y * cos + z * sin,
                y * -sin + z * cos
        )
    }

    fun rotateYaxis(radian: Double): Vector3 {
        val sin = Math.sin(radian)
        val cos = Math.cos(radian)
        return Vector3(
                x * cos - z * sin,
                y,
                x * sin + z * cos
        )
    }

    fun rotateZaxis(radian: Double): Vector3 {
        val sin = Math.sin(radian)
        val cos = Math.cos(radian)
        return Vector3(
                x * cos + y * sin,
                x * -sin + y * cos,
                z
        )
    }

}
