package com.gmail.jiangyang5157.kotlin_kit.math

/**
 * Created by Yang Jiang on June 27, 2017
 */
data class VectorD3(val x: Double, val y: Double, val z: Double) {

    constructor() : this(0.0, 0.0, 0.0)

    constructor(double: Double) : this(double, double, double)

    operator fun unaryMinus(): VectorD3 = VectorD3(-x, -y, -z)

    operator fun plus(other: VectorD3): VectorD3 = VectorD3(x + other.x, y + other.y, z + other.z)

    operator fun plus(int: Int): VectorD3 = VectorD3(x + int, y + int, z + int)

    operator fun plus(double: Double): VectorD3 = VectorD3(x + double, y + double, z + double)

    operator fun minus(other: VectorD3): VectorD3 = VectorD3(x - other.x, y - other.y, z - other.z)

    operator fun minus(int: Int): VectorD3 = VectorD3(x - int, y - int, z - int)

    operator fun minus(double: Double): VectorD3 = VectorD3(x - double, y - double, z - double)

    operator fun times(other: VectorD3): VectorD3 = VectorD3(x * other.x, y * other.y, z * other.z)

    operator fun times(int: Int): VectorD3 = VectorD3(x * int, y * int, z * int)

    operator fun times(double: Double): VectorD3 = VectorD3(x * double, y * double, z * double)

    operator fun div(other: VectorD3): VectorD3 = VectorD3(x / other.x, y / other.y, z / other.z)

    operator fun div(int: Int): VectorD3 = VectorD3(x / int, y / int, z / int)

    operator fun div(double: Double): VectorD3 = VectorD3(x / double, y / double, z / double)

    val length: Double
        get() = Math.sqrt(this.dot(this))

    val normalize: VectorD3
        get() = this / length

    fun dot(other: VectorD3): Double = x * other.x + y * other.y + z * other.z

    fun cross(other: VectorD3): VectorD3 = VectorD3(
            y * other.z - z * other.y,
            z * other.x - x * other.z,
            x * other.y - y * other.x
    )

    fun alpha(): Double = Math.atan2(y, x)

    fun delta(): Double = Math.asin(z / length)

    fun radian(other: VectorD3): Double {
        var r = dot(other) * (1.0 / (length * other.length))
        if (r < -1.0) {
            r = -1.0
        } else if (r > 1.0) {
            r = 1.0
        }
        return Math.acos(r)
    }

    fun rotateXaxis(radian: Double): VectorD3 {
        val sin = Math.sin(radian)
        val cos = Math.cos(radian)
        return VectorD3(
                x,
                y * cos + z * sin,
                y * -sin + z * cos
        )
    }

    fun rotateYaxis(radian: Double): VectorD3 {
        val sin = Math.sin(radian)
        val cos = Math.cos(radian)
        return VectorD3(
                x * cos - z * sin,
                y,
                x * sin + z * cos
        )
    }

    fun rotateZaxis(radian: Double): VectorD3 {
        val sin = Math.sin(radian)
        val cos = Math.cos(radian)
        return VectorD3(
                x * cos + y * sin,
                x * -sin + y * cos,
                z
        )
    }

}
