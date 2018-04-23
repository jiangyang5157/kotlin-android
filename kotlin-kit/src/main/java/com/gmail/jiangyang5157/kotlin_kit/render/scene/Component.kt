package com.gmail.jiangyang5157.kotlin_kit.render.scene

/**
 * Created by Yang Jiang on April 24, 2018
 */
interface Component<T : Position> {

    val position: T

    val components: MutableList<Component<T>>

}