package com.gmail.jiangyang5157.kotlin.net.color

import okhttp3.*

/**
 * Created by Yang Jiang on July 12, 2019
 */
class ColorServiceInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val uri = chain.request().url().uri().toString()
        val responseString = when {
            uri.endsWith("color.json") -> colorJson
            uri.endsWith("colors.json") -> colorsJson
            else -> ""
        }

        return chain.proceed(chain.request())
                .newBuilder()
                .code(200)
                .protocol(Protocol.HTTP_2)
                .message(responseString)
                .body(
                        ResponseBody.create(
                                MediaType.parse("application/json"),
                                responseString.toByteArray()
                        )
                )
                .addHeader("content-type", "application/json")
                .build()
    }
}

const val colorJson = """
{
    "color": "black",
    "category": "hue",
    "type": "primary",
    "code": {
        "rgba": [255,255,255,1],
        "hex": "#000"
    }
}
"""

const val colorsJson = """
{
  "color": [
    {
      "color": "black",
      "category": "hue",
      "type": "primary",
      "code": {
        "rgba": [255,255,255,1],
        "hex": "#000"
      }
    },
    {
      "color": "white",
      "category": "value",
      "code": {
        "rgba": [0,0,0,1],
        "hex": "#FFF"
      }
    },
    {
      "color": "red",
      "category": "hue",
      "type": "primary",
      "code": {
        "rgba": [255,0,0,1],
        "hex": "#FF0"
      }
    },
    {
      "color": "blue",
      "category": "hue",
      "type": "primary",
      "code": {
        "rgba": [0,0,255,1],
        "hex": "#00F"
      }
    },
    {
      "color": "yellow",
      "category": "hue",
      "type": "primary",
      "code": {
        "rgba": [255,255,0,1],
        "hex": "#FF0"
      }
    },
    {
      "color": "green",
      "category": "hue",
      "type": "secondary",
      "code": {
        "rgba": [0,255,0,1],
        "hex": "#0F0"
      }
    }
  ]
}
"""
