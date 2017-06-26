package com.gmail.jiangyang5157.kotlin.math

/**
 * Created by Yang Jiang on June 26, 2017
 */
data class Point3(val x: Double, val y: Double, val z: Double) {

    constructor() : this(0.0, 0.0, 0.0)
    constructor(d: Double) : this(d, d, d)
    constructor(p: Point3) : this(p.x, p.y, p.z)

    operator fun unaryMinus(): Point3 = Point3(-x, -y, -z)

    operator fun plus(other: Point3): Point3 = Point3(x + other.x, y + other.y, z + other.z)
    operator fun plus(other: Int): Point3 = Point3(x + other, y + other, z + other)
    operator fun plus(other: Double): Point3 = Point3(x + other, y + other, z + other)

    operator fun minus(other: Point3): Point3 = Point3(x - other.x, y - other.y, z - other.z)
    operator fun minus(other: Int): Point3 = Point3(x - other, y - other, z - other)
    operator fun minus(other: Double): Point3 = Point3(x - other, y - other, z - other)

    operator fun times(other: Point3): Point3 = Point3(x * other.x, y * other.y, z * other.z)
    operator fun times(other: Int): Point3 = Point3(x * other, y * other, z * other)
    operator fun times(other: Double): Point3 = Point3(x * other, y * other, z * other)

    operator fun div(other: Point3): Point3 = Point3(x / other.x, y / other.y, z / other.z)
    operator fun div(other: Int): Point3 = Point3(x / other, y / other, z / other)
    operator fun div(other: Double): Point3 = Point3(x / other, y / other, z / other)

}