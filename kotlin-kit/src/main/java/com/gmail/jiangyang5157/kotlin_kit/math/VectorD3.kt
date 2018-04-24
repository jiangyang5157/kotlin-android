package com.gmail.jiangyang5157.kotlin_kit.math

/**
 * Created by Yang Jiang on June 27, 2017
 */
data class VectorD3(override val x: Double, override val y: Double, override val z: Double) : Vector3<Double> {

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

    override val length: Double
        get() = Math.sqrt(this.dot(this))

    override val normalize: VectorD3
        get() = this / length

    override fun dot(other: Vector3<Double>): Double = x * other.x + y * other.y + z * other.z
    override fun cross(other: Vector3<Double>): VectorD3 = VectorD3(
            y * other.z - z * other.y,
            z * other.x - x * other.z,
            x * other.y - y * other.x
    )

    override fun alpha(): Double = Math.atan2(y, x)
    override fun delta(): Double = Math.asin(z / length)

    override fun radian(other: Vector3<Double>): Double {
        var r = dot(other) * (1.0 / (length * other.length))
        if (r < -1.0) {
            r = -1.0
        } else if (r > 1.0) {
            r = 1.0
        }
        return Math.acos(r)
    }

    override fun xRotation(radian: Double): VectorD3 {
        val sin = Math.sin(radian)
        val cos = Math.cos(radian)
        return VectorD3(
                x,
                y * cos + z * sin,
                y * -sin + z * cos
        )
    }

    override fun yRotation(radian: Double): VectorD3 {
        val sin = Math.sin(radian)
        val cos = Math.cos(radian)
        return VectorD3(
                x * cos - z * sin,
                y,
                x * sin + z * cos
        )
    }

    override fun zRotation(radian: Double): VectorD3 {
        val sin = Math.sin(radian)
        val cos = Math.cos(radian)
        return VectorD3(
                x * cos + y * sin,
                x * -sin + y * cos,
                z
        )
    }
}
