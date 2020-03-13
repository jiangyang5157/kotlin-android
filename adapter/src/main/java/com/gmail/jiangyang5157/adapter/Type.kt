package com.gmail.jiangyang5157.adapter

data class Type<T>(
  val clazz: Class<out T>,
  val delegate: ItemViewDelegate<T, *>
)
