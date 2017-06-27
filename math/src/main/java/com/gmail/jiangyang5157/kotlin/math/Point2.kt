package com.gmail.jiangyang5157.kotlin.math

/**
 * Created by Yang Jiang on June 26, 2017
 */
data class Point2(val x: Double, val y: Double) {

    constructor() : this(0.0, 0.0)
    constructor(d: Double) : this(d, d)

    operator fun unaryMinus(): Point2 = Point2(-x, -y)

    operator fun plus(other: Point2): Point2 = Point2(x + other.x, y + other.y)
    operator fun plus(int: Int): Point2 = Point2(x + int, y + int)
    operator fun plus(double: Double): Point2 = Point2(x + double, y + double)

    operator fun minus(other: Point2): Point2 = Point2(x - other.x, y - other.y)
    operator fun minus(int: Int): Point2 = Point2(x - int, y - int)
    operator fun minus(double: Double): Point2 = Point2(x - double, y - double)

    operator fun times(other: Point2): Point2 = Point2(x * other.x, y * other.y)
    operator fun times(int: Int): Point2 = Point2(x * int, y * int)
    operator fun times(double: Double): Point2 = Point2(x * double, y * double)

    operator fun div(other: Point2): Point2 = Point2(x / other.x, y / other.y)
    operator fun div(int: Int): Point2 = Point2(x / int, y / int)
    operator fun div(double: Double): Point2 = Point2(x / double, y / double)

}