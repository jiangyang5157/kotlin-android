package com.gmail.jiangyang5157.kotlin_kit.math

/**
 * Created by Yang Jiang on April 25, 2018
 */
interface Point2<out N : Number> : Point {
    val x: N
    val y: N
}
