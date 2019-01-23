package com.gmail.jiangyang5157.kotlin_kit.model.finance

import org.junit.Test
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

/**
 * Created by Yang Jiang on January 23, 2019
 */
class MoneyTest {

    @Test
    fun test_constructor_fraction_digits_2() {
        val countryCode = "USD"
        val countryCurrency = Currency.getInstance(Locale.US)

        assertEquals("0.00", Money(0, countryCode).amount.toString())
        assertEquals("0.00", Money(0, countryCurrency).amount.toString())
        assertEquals("0.00", Money(-0, countryCode).amount.toString())
        assertEquals("0.00", Money(-0, countryCurrency).amount.toString())

        assertEquals("1234567.89", Money(123456789, countryCode).amount.toString())
        assertEquals("1234567.89", Money(123456789, countryCurrency).amount.toString())
        assertEquals("-1234567.89", Money(-123456789, countryCode).amount.toString())
        assertEquals("-1234567.89", Money(-123456789, countryCurrency).amount.toString())

        assertEquals("123.00", Money(123.0, countryCode).amount.toString())
        assertEquals("123.00", Money(123.0, countryCurrency).amount.toString())
        assertEquals("-123.00", Money(-123.0, countryCode).amount.toString())
        assertEquals("-123.00", Money(-123.0, countryCurrency).amount.toString())

        assertEquals("123.46", Money(123.451, countryCode).amount.toString())
        assertEquals("123.46", Money(123.451, countryCurrency).amount.toString())
        assertEquals("-123.46", Money(-123.451, countryCode).amount.toString())
        assertEquals("-123.46", Money(-123.451, countryCurrency).amount.toString())
    }

    @Test
    fun test_constructor_fraction_digits_0() {
        val countryCode = "JPY"
        val countryCurrency = Currency.getInstance(Locale.JAPAN)

        assertEquals("0", Money(0, countryCode).amount.toString())
        assertEquals("0", Money(0, countryCurrency).amount.toString())
        assertEquals("0", Money(-0, countryCode).amount.toString())
        assertEquals("0", Money(-0, countryCurrency).amount.toString())

        assertEquals("123456789", Money(123456789, countryCode).amount.toString())
        assertEquals("123456789", Money(123456789, countryCurrency).amount.toString())
        assertEquals("-123456789", Money(-123456789, countryCode).amount.toString())
        assertEquals("-123456789", Money(-123456789, countryCurrency).amount.toString())

        assertEquals("123", Money(123.0, countryCode).amount.toString())
        assertEquals("123", Money(123.0, countryCurrency).amount.toString())
        assertEquals("-123", Money(-123.0, countryCode).amount.toString())
        assertEquals("-123", Money(-123.0, countryCurrency).amount.toString())

        assertEquals("124", Money(123.451, countryCode).amount.toString())
        assertEquals("124", Money(123.451, countryCurrency).amount.toString())
        assertEquals("-124", Money(-123.451, countryCode).amount.toString())
        assertEquals("-124", Money(-123.451, countryCurrency).amount.toString())
    }

    @Test
    fun test_equality() {
        assertEquals(Money(0, "USD"), Money(0, "USD"))
        assertEquals(Money(12345678900, "USD"), Money(123456789.00, "USD"))
        assertEquals(Money(123456789, "JPY"), Money(123456789.00, "JPY"))
        assertEquals(Money(2, "JPY"), Money(1.1, "JPY"))
        assertEquals(Money(123.46, "USD"), Money(123.451, "USD"))

        assertNotEquals(Money(0, "JPY"), Money(0, "USD"))
        assertNotEquals(Money(1234.56, "USD"), Money(1234.561, "USD"))
        assertNotEquals(Money(1, "JPY"), Money(1.1, "JPY"))
        assertNotEquals(Money(1, "JPY"), Money(1.1, "JPY"))
        assertNotEquals(Money(123.45, "USD"), Money(123.451, "USD"))
    }

}