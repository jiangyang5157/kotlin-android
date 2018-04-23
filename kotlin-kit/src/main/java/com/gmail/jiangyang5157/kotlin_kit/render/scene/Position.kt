package com.gmail.jiangyang5157.kotlin_kit.render.scene

import com.gmail.jiangyang5157.kotlin_kit.math.PointD2
import com.gmail.jiangyang5157.kotlin_kit.math.PointD3
import com.gmail.jiangyang5157.kotlin_kit.math.PointI2
import com.gmail.jiangyang5157.kotlin_kit.math.PointI3

/**
 * Created by Yang Jiang on April 24, 2018
 */
interface Position

interface PositionI2 : Position {
    val o: PointI2
    val w: Int
    val h: Int
}

interface PositionD2 : Position {
    val o: PointD2
    val w: Double
    val h: Double
}

interface PositionI3 : Position {
    val o: PointI3
    val w: Int
    val h: Int
    val d: Int
}

interface PositionD3 : Position {
    val o: PointD3
    val w: Double
    val h: Double
    val d: Double
}
