package com.gmail.jiangyang5157.kotlin_kit.data.adapter

import com.gmail.jiangyang5157.kotlin_kit.data.model.Converter
import com.gmail.jiangyang5157.kotlin_kit.data.model.finance.Money

open class MoneyDoubleConverter : Converter<Money, Double> {

    override fun forward(a: Money?): Double? {
        return a?.amount?.toDouble()
    }

    override fun backward(b: Double?): Money? {
        return b?.let { Money(it) }
    }
}
