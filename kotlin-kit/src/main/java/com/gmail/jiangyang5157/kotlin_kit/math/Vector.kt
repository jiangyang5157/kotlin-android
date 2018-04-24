package com.gmail.jiangyang5157.kotlin_kit.math

/**
 * Created by Yang Jiang on April 24, 2018
 */
interface Vector

interface Vector2<T : Number> : Vector {
    val x: T
    val y: T

    val length: Double
    val normalize: Vector2<T>

    fun dot(other: Vector2<T>): Double
    fun cross(other: Vector2<T>): Double

    fun alpha(): Double
    fun rotate(radian: Double): Vector2<T>
}

interface Vector3<T : Number> : Vector {
    val x: T
    val y: T
    val z: T

    val length: Double
    val normalize: Vector3<T>

    fun dot(other: Vector3<T>): Double
    fun cross(other: Vector3<T>): Vector3<T>

    fun alpha(): Double
    fun delta(): Double

    fun radian(other: Vector3<T>): Double
    fun xRotation(radian: Double): Vector3<T>
    fun yRotation(radian: Double): Vector3<T>
    fun zRotation(radian: Double): Vector3<T>
}
