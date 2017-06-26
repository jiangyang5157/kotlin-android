package com.gmail.jiangyang5157.kotlin.math

/**
 * Created by Yang Jiang on June 26, 2017
 */
data class Point2(val x: Double = 0.0, val y: Double = 0.0) {

    operator fun unaryMinus(): Point2 = Point2(-x, -y)

    operator fun plus(other: Point2): Point2 = Point2(x + other.x, y + other.y)
    operator fun plus(other: Int): Point2 = Point2(x + other, y + other)
    operator fun plus(other: Double): Point2 = Point2(x + other, y + other)

    operator fun minus(other: Point2): Point2 = Point2(x - other.x, y - other.y)
    operator fun minus(other: Int): Point2 = Point2(x - other, y - other)
    operator fun minus(other: Double): Point2 = Point2(x - other, y - other)

    operator fun times(other: Point2): Point2 = Point2(x * other.x, y * other.y)
    operator fun times(other: Int): Point2 = Point2(x * other, y * other)
    operator fun times(other: Double): Point2 = Point2(x * other, y * other)

    operator fun div(other: Point2): Point2 = Point2(x / other.x, y / other.y)
    operator fun div(other: Int): Point2 = Point2(x / other, y / other)
    operator fun div(other: Double): Point2 = Point2(x / other, y / other)

}