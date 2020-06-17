package com.gmail.jiangyang5157.kotlin_kit.data.adapter

import com.google.gson.*
import java.lang.reflect.Type
import java.util.*

/**
 * Example:
 *
 * class MyDateJsonSerializer : DateJsonSerializer() {
 *      override val pattern: String
 *          get() = RegexUtils.DATE_DMY
 * }
 */
abstract class DateJsonSerializer : JsonSerializer<Date>, JsonDeserializer<Date> {

    abstract val pattern: String

    override fun serialize(
        date: Date?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return DateStringConverter(
            pattern
        ).forward(
            date
        )?.let {
            JsonPrimitive(it)
        } ?: throw IllegalArgumentException("Cannot serialize $date to [JsonElement]")
    }

    override fun deserialize(
        src: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Date {
        return DateStringConverter(
            pattern
        ).backward(
            src?.asString
        ) ?: throw IllegalArgumentException("Cannot deserialize $src to [Date]")
    }
}
