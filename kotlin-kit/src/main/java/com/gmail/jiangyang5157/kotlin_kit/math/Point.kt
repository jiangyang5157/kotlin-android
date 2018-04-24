package com.gmail.jiangyang5157.kotlin_kit.math

/**
 * Created by Yang Jiang on April 24, 2018
 */
interface Point

interface Point2<out T : Number> : Point {
    val x: T
    val y: T
}

interface Point3<out T : Number> : Point {
    val x: T
    val y: T
    val z: T
}
