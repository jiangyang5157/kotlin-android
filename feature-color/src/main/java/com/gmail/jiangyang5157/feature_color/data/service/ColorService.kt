package com.gmail.jiangyang5157.feature_color.data.service

import androidx.lifecycle.LiveData
import com.gmail.jiangyang5157.core.network.ApiResponse
import com.gmail.jiangyang5157.core.network.LiveDataCallAdapterFactory
import com.gmail.jiangyang5157.feature_color.domain.entity.Color
import com.gmail.jiangyang5157.feature_color.domain.entity.Colors
import com.google.gson.GsonBuilder
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ColorService {

    @GET("color.json")
    fun fetchColor(): LiveData<ApiResponse<Color>>

    @GET("colors.json")
    fun fetchColors(): LiveData<ApiResponse<Colors>>

    class Builder {
        /**
         * No available service at this moment, using fake service to fit in
         */
        fun build(): ColorService {
            return Retrofit.Builder()
                .baseUrl("https://www.google.com/")
                .client(
                    OkHttpClient.Builder()
                        .addInterceptor(ColorServiceInterceptor())
                        .build()
                )
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .addConverterFactory(
                    GsonConverterFactory.create(
                        GsonBuilder().setLenient().create()
                    )
                )
                .build()
                .create(ColorService::class.java)
        }
    }
}

class ColorServiceInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val uri = chain.request().url().uri().toString()
        val responseString = when {
            uri.endsWith("color.json") -> mock_color
            uri.endsWith("colors.json") -> mock_colors
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

const val mock_color = """
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

const val mock_colors = """
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
