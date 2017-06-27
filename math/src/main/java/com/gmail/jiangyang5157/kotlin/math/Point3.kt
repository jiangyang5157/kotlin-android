package com.gmail.jiangyang5157.kotlin.math

/**
 * Created by Yang Jiang on June 26, 2017
 */
data class Point3(val x: Double, val y: Double, val z: Double) {

    constructor() : this(0.0, 0.0, 0.0)
    constructor(d: Double) : this(d, d, d)

    operator fun unaryMinus(): Point3 = Point3(-x, -y, -z)

    operator fun plus(other: Point3): Point3 = Point3(x + other.x, y + other.y, z + other.z)
    operator fun plus(int: Int): Point3 = Point3(x + int, y + int, z + int)
    operator fun plus(double: Double): Point3 = Point3(x + double, y + double, z + double)

    operator fun minus(other: Point3): Point3 = Point3(x - other.x, y - other.y, z - other.z)
    operator fun minus(int: Int): Point3 = Point3(x - int, y - int, z - int)
    operator fun minus(double: Double): Point3 = Point3(x - double, y - double, z - double)

    operator fun times(other: Point3): Point3 = Point3(x * other.x, y * other.y, z * other.z)
    operator fun times(int: Int): Point3 = Point3(x * int, y * int, z * int)
    operator fun times(double: Double): Point3 = Point3(x * double, y * double, z * double)

    operator fun div(other: Point3): Point3 = Point3(x / other.x, y / other.y, z / other.z)
    operator fun div(int: Int): Point3 = Point3(x / int, y / int, z / int)
    operator fun div(double: Double): Point3 = Point3(x / double, y / double, z / double)

}