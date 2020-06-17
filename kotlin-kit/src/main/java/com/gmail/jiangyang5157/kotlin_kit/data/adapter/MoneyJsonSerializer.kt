package com.gmail.jiangyang5157.kotlin_kit.data.adapter

import com.gmail.jiangyang5157.kotlin_kit.data.model.finance.Money
import com.google.gson.*
import java.lang.reflect.Type

open class MoneyJsonSerializer : JsonSerializer<Money>, JsonDeserializer<Money> {

    override fun serialize(
        money: Money?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return MoneyDoubleConverter().forward(
            money
        )?.let {
            JsonPrimitive(it)
        } ?: throw IllegalArgumentException("Cannot serialize $money to [JsonElement]")
    }

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Money {
        return MoneyDoubleConverter().backward(
            json?.asDouble
        ) ?: throw IllegalArgumentException("Cannot deserialize $json to [Money] as Double")
    }
}
