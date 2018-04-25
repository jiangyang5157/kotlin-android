package com.gmail.jiangyang5157.kotlin_kit.render.scene

import com.gmail.jiangyang5157.kotlin_kit.render.scene.shape.Shape

/**
 * Created by Yang Jiang on April 25, 2018
 */
interface Projection<in S : Shape, in Specification, in Texture> {

    fun map(shape: S, spec: Specification, texture: Texture)

}
