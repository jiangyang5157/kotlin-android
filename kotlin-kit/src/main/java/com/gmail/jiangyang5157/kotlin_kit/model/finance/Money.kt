package com.gmail.jiangyang5157.kotlin_kit.model.finance

import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*

/**
 * Created by Yang Jiang on January 22, 2019
 *
 * Apply ISO 4217 code
 * Apply default fraction digits associated with the currency
 * Apply java.math.RoundingMode.UP
 */
data class Money(val amount: BigDecimal, val currency: Currency) {

    constructor(amount: Long, currency: Currency) :
            this(BigDecimal.valueOf(amount, currency.defaultFractionDigits), currency)

    constructor(amount: Long, currencyCode: String) :
            this(amount, Currency.getInstance(currencyCode))

    constructor(amount: Double, currency: Currency) :
            this(BigDecimal.valueOf(amount).setScale(currency.defaultFractionDigits, RoundingMode.UP), currency)

    constructor(amount: Double, currencyCode: String) :
            this(amount, Currency.getInstance(currencyCode))

//    operator fun unaryMinus(): Money = Money(-x, -y)
//    operator fun plus(other: Money): Money = Money(x + other.x, y + other.y)
//    operator fun plus(int: Int): Money = Money(x + int, y + int)
//    operator fun plus(double: Double): Money = Money(x + double, y + double)
//    operator fun minus(other: Money): Money = Money(x - other.x, y - other.y)
//    operator fun minus(int: Int): Money = Money(x - int, y - int)
//    operator fun minus(double: Double): Money = Money(x - double, y - double)
//    operator fun times(other: Money): Money = Money(x * other.x, y * other.y)
//    operator fun times(int: Int): Money = Money(x * int, y * int)
//    operator fun times(double: Double): Money = Money(x * double, y * double)
//    operator fun div(other: Money): Money = Money(x / other.x, y / other.y)
//    operator fun div(int: Int): Money = Money(x / int, y / int)
//    operator fun div(double: Double): Money = Money(x / double, y / double)

//    public Money add(Money other) throws Exception{
//        assertSameCurrencyAs( other );
//        return newMoney(amount.add(other.amount, DEFAULT_CONTEXT));
//    }
//
//
//    public Money multiply(BigDecimal amount) {
//        return new Money( this.amount().multiply(amount, DEFAULT_CONTEXT), currency);
//    }
//
//    public Money multiply( BigDecimal amount, RoundingMode roundingMode ) {
//        MathContext ct = new MathContext( currency.getDefaultFractionDigits(), roundingMode );
//        return new Money( amount().multiply(amount, ct), currency);
//    }
//
//    private Money newMoney(BigDecimal amount) {
//        return new Money( amount, this.currency );
//    }
//
//    public Money multiply(double amount) {
//        return multiply( new BigDecimal( amount ) );
//    }
//
//    public Money subtract(Money other) throws Exception {
//        assertSameCurrencyAs(other);
//        return newMoney( amount.subtract(other.amount, DEFAULT_CONTEXT) );
//    }
//
//    public int compareTo(Object other) throws Exception {
//        return compareTo((Money) other);
//    }
//
//    public boolean greaterThan(Money other)throws Exception {
//        return (compareTo(other) > 0);
//    }
//
//    public Money divideByNumber( double divisor){
//        BigDecimal div = BigDecimal.valueOf( divisor );
//        BigDecimal ans = this.amount.divide(div, DEFAULT_CONTEXT);
//        return new Money(ans, this.currency);
//    }
}