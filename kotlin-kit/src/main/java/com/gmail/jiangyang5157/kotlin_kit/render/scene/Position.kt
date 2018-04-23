package com.gmail.jiangyang5157.kotlin_kit.render.scene

import com.gmail.jiangyang5157.kotlin_kit.math.VectorD2
import com.gmail.jiangyang5157.kotlin_kit.math.VectorD3
import com.gmail.jiangyang5157.kotlin_kit.math.VectorI2
import com.gmail.jiangyang5157.kotlin_kit.math.VectorI3

/**
 * Created by Yang Jiang on April 24, 2018
 */
interface Position

interface PositionD2 : Position {
    val o: VectorD2
    val w: Double
    val h: Double
}

interface PositionI2 : Position {
    val o: VectorI2
    val w: Int
    val h: Int
}

interface PositionD3 : Position {
    val o: VectorD3
    val w: Double
    val h: Double
    val d: Double
}

interface PositionI3 : Position {
    val o: VectorI3
    val w: Int
    val h: Int
    val d: Int
}