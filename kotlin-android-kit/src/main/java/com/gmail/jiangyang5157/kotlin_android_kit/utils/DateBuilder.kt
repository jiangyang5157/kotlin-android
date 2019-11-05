package com.gmail.jiangyang5157.kotlin_android_kit.utils

import android.net.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Created by Yang Jiang on July 01, 2017
 */
object DateBuilder {

    fun buildStringDate(template: String): String = buildDateFormat(template).format(Date())

    fun buildStringDate(milliseconds: Long, template: String): String = buildDateFormat(template).format(Date(milliseconds))

    fun buildLongDate(): Long = Date().time

    @Throws(ParseException::class)
    fun buildLongDate(stringDate: String, template: String): Long = buildDateFormat(template).parse(stringDate).time

    fun buildDateFormat(template: String, locale: Locale = Locale.getDefault()): SimpleDateFormat = SimpleDateFormat(template, locale)
}
