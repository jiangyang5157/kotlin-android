package com.gmail.jiangyang5157.kotlin_kit.data.adapter

import com.gmail.jiangyang5157.kotlin_kit.data.model.finance.Money
import org.junit.Assert
import org.junit.Test

class MoneyDoubleConverterTest {

    @Test
    fun test_backward_common() {
        Assert.assertEquals(-1.00, MoneyDoubleConverter().backward(-1.0)?.amount?.toDouble())
        Assert.assertEquals(-0.99, MoneyDoubleConverter().backward(-.99)?.amount?.toDouble())
    }

    @Test
    fun test_forward_common() {
        Assert.assertEquals(-1.00, MoneyDoubleConverter().forward(Money(-1.0)))
    }

    @Test
    fun test_backward_round() {
        Assert.assertEquals(-2.00, MoneyDoubleConverter().backward(-1.999)?.amount?.toDouble())
    }

    @Test
    fun test_backward_invalid() {
        Assert.assertNull(MoneyDoubleConverter().backward(null))
    }

    @Test
    fun test_forward_invalid() {
        Assert.assertNull(MoneyDoubleConverter().forward(null))
    }
}
