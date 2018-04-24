package com.gmail.jiangyang5157.kotlin_kit.math

/**
 * Created by Yang Jiang on April 24, 2018
 */
interface Vector

interface Vector2<Num : Number> : Vector {
    val x: Num
    val y: Num

    val length: Double
    val normalize: Vector2<Num>

    fun dot(other: Vector2<Num>): Double
    fun cross(other: Vector2<Num>): Double

    fun alpha(): Double
    fun rotate(radian: Double): Vector2<Num>
}

interface Vector3<Num : Number> : Vector {
    val x: Num
    val y: Num
    val z: Num

    val length: Double
    val normalize: Vector3<Num>

    fun dot(other: Vector3<Num>): Double
    fun cross(other: Vector3<Num>): Vector3<Num>

    fun alpha(): Double
    fun delta(): Double

    fun radian(other: Vector3<Num>): Double
    fun xRotation(radian: Double): Vector3<Num>
    fun yRotation(radian: Double): Vector3<Num>
    fun zRotation(radian: Double): Vector3<Num>
}
