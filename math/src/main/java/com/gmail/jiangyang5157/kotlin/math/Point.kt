package com.gmail.jiangyang5157.kotlin.math

/**
 * Created by Yang Jiang on June 26, 2017
 */
data class Point(val x: Double, val y: Double) {

    operator fun unaryMinus(): Point = Point(-x, -y)

    operator fun plus(other: Point): Point = Point(x + other.x, y + other.y)
    operator fun plus(other: Int): Point = Point(x + other, y + other)
    operator fun plus(other: Double): Point = Point(x + other, y + other)

    operator fun minus(other: Point): Point = Point(x - other.x, y - other.y)
    operator fun minus(other: Int): Point = Point(x - other, y - other)
    operator fun minus(other: Double): Point = Point(x - other, y - other)

    operator fun times(other: Point): Point = Point(x * other.x, y * other.y)
    operator fun times(other: Int): Point = Point(x * other, y * other)
    operator fun times(other: Double): Point = Point(x * other, y * other)

    operator fun div(other: Point): Point = Point(x / other.x, y / other.y)
    operator fun div(other: Int): Point = Point(x / other, y / other)
    operator fun div(other: Double): Point = Point(x / other, y / other)

}