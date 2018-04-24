package com.gmail.jiangyang5157.kotlin_kit.math

/**
 * Created by Yang Jiang on April 24, 2018
 */
data class VectorI3(val x: Int, val y: Int, val z: Int) : Vector {

    constructor() : this(0, 0, 0)

    constructor(int: Int) : this(int, int, int)

    operator fun unaryMinus(): VectorI3 = VectorI3(-x, -y, -z)

    operator fun plus(other: VectorI3): VectorI3 = VectorI3(x + other.x, y + other.y, z + other.z)

    operator fun plus(int: Int): VectorI3 = VectorI3(x + int, y + int, z + int)

    operator fun plus(double: Double): VectorI3 = VectorI3((x + double).toInt(), (y + double).toInt(), (z + double).toInt())

    operator fun minus(other: VectorI3): VectorI3 = VectorI3(x - other.x, y - other.y, z - other.z)

    operator fun minus(int: Int): VectorI3 = VectorI3(x - int, y - int, z - int)

    operator fun minus(double: Double): VectorI3 = VectorI3((x - double).toInt(), (y - double).toInt(), (z - double).toInt())

    operator fun times(other: VectorI3): VectorI3 = VectorI3(x * other.x, y * other.y, z * other.z)

    operator fun times(int: Int): VectorI3 = VectorI3(x * int, y * int, z * int)

    operator fun times(double: Double): VectorI3 = VectorI3((x * double).toInt(), (y * double).toInt(), (z * double).toInt())

    operator fun div(other: VectorI3): VectorI3 = VectorI3(x / other.x, y / other.y, z / other.z)

    operator fun div(int: Int): VectorI3 = VectorI3(x / int, y / int, z / int)

    operator fun div(double: Double): VectorI3 = VectorI3((x / double).toInt(), (y / double).toInt(), (z / double).toInt())

    val length: Double
        get() = Math.sqrt(this.dot(this))

    val normalize: VectorI3
        get() = this / length

    fun dot(other: VectorI3): Double = (x * other.x + y * other.y + z * other.z).toDouble()

    fun cross(other: VectorI3): VectorI3 = VectorI3(
            y * other.z - z * other.y,
            z * other.x - x * other.z,
            x * other.y - y * other.x
    )

    fun alpha(): Double = Math.atan2(y.toDouble(), x.toDouble())

    fun delta(): Double = Math.asin(z / length)

    fun radian(other: VectorI3): Double {
        var r = dot(other) * (1.0 / (length * other.length))
        if (r < -1.0) {
            r = -1.0
        } else if (r > 1.0) {
            r = 1.0
        }
        return Math.acos(r)
    }

    fun rotateXaxis(radian: Double): VectorI3 {
        val sin = Math.sin(radian)
        val cos = Math.cos(radian)
        return VectorI3(
                x,
                (y * cos + z * sin).toInt(),
                (y * -sin + z * cos).toInt()
        )
    }

    fun rotateYaxis(radian: Double): VectorI3 {
        val sin = Math.sin(radian)
        val cos = Math.cos(radian)
        return VectorI3(
                (x * cos - z * sin).toInt(),
                y,
                (x * sin + z * cos).toInt()
        )
    }

    fun rotateZaxis(radian: Double): VectorI3 {
        val sin = Math.sin(radian)
        val cos = Math.cos(radian)
        return VectorI3(
                (x * cos + y * sin).toInt(),
                (x * -sin + y * cos).toInt(),
                z
        )
    }

}