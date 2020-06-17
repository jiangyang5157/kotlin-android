package com.gmail.jiangyang5157.kotlin_kit.data.model

interface Converter<A, B> {

    fun forward(a: A?): B?

    fun backward(b: B?): A?
}
