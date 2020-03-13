package com.gmail.jiangyang5157.feature_color.service

import androidx.lifecycle.LiveData
import com.gmail.jiangyang5157.architecture.network.ApiResponse
import com.gmail.jiangyang5157.feature_color.domain.entity.Color
import com.gmail.jiangyang5157.feature_color.domain.entity.Colors
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.http.GET

interface ColorService {

    companion object {
        const val baseUrl = "https://www.google.com/"
    }

    @GET("color.json")
    fun fetchColor(): LiveData<ApiResponse<Color>>

    @GET("colors.json")
    fun fetchColors(): LiveData<ApiResponse<Colors>>
}

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
    "value": {
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
      "value": {
        "rgba": [255,255,255,1],
        "hex": "#000"
      }
    },
    {
      "color": "white",
      "category": "value",
      "value": {
        "rgba": [0,0,0,1],
        "hex": "#FFF"
      }
    },
    {
      "color": "red",
      "category": "hue",
      "type": "primary",
      "value": {
        "rgba": [255,0,0,1],
        "hex": "#FF0"
      }
    },
    {
      "color": "blue",
      "category": "hue",
      "type": "primary",
      "value": {
        "rgba": [0,0,255,1],
        "hex": "#00F"
      }
    },
    {
      "color": "yellow",
      "category": "hue",
      "type": "primary",
      "value": {
        "rgba": [255,255,0,1],
        "hex": "#FF0"
      }
    },
    {
      "color": "green",
      "category": "hue",
      "type": "secondary",
      "value": {
        "rgba": [0,255,0,1],
        "hex": "#0F0"
      }
    }
  ]
}
"""
