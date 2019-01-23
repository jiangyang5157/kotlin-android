package com.gmail.jiangyang5157.kotlin_kit.model.finance

import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*

/**
 * Created by Yang Jiang on January 22, 2019
 *
 * Apply ISO 4217 code
 * Apply default fraction digits associated with the currency
 * Apply java.math.RoundingMode.HALF_UP
 */
data class Money(val amount: BigDecimal, val currency: Currency) {

    constructor(amount: Long, currency: Currency) :
            this(BigDecimal.valueOf(amount, currency.defaultFractionDigits), currency)

    constructor(amount: Long, currencyCode: String) :
            this(amount, Currency.getInstance(currencyCode))

    constructor(amount: Double, currency: Currency) :
            this(BigDecimal.valueOf(amount).setScale(currency.defaultFractionDigits, RoundingMode.HALF_UP), currency)

    constructor(amount: Double, currencyCode: String) :
            this(amount, Currency.getInstance(currencyCode))

    operator fun unaryMinus(): Money = Money(-amount, currency)

    @Throws(IllegalArgumentException::class)
    operator fun plus(other: Money): Money {
        if (currency != other.currency) {
            throw IllegalArgumentException("Same currency is required.")
        }
        return Money(amount.add(other.amount), currency)
    }

    @Throws(IllegalArgumentException::class)
    operator fun minus(other: Money): Money {
        if (currency != other.currency) {
            throw IllegalArgumentException("Same currency is required.")
        }
        return Money(amount.subtract(other.amount), currency)
    }

    operator fun times(n: Long): Money {
        return Money(amount.multiply(BigDecimal.valueOf(n)).setScale(currency.defaultFractionDigits, RoundingMode.HALF_UP), currency)
    }

    operator fun times(n: Double): Money {
        return Money(amount.multiply(BigDecimal.valueOf(n)).setScale(currency.defaultFractionDigits, RoundingMode.HALF_UP), currency)
    }

    operator fun div(n: Long): Money {
        return Money(amount.divide(BigDecimal.valueOf(n), currency.defaultFractionDigits, RoundingMode.HALF_UP), currency)
    }

    operator fun div(n: Double): Money {
        return Money(amount.divide(BigDecimal.valueOf(n), currency.defaultFractionDigits, RoundingMode.HALF_UP), currency)
    }
}