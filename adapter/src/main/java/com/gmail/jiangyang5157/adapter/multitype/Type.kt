package com.gmail.jiangyang5157.adapter.multitype

data class Type<T>(
  val clazz: Class<out T>,
  val delegate: ItemViewDelegate<T, *>
)
