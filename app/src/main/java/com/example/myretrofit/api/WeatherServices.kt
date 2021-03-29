package com.example.myretrofit.api

import com.example.myretrofit.model.WeatherData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface  WeatherServices {
    @GET("weather")
    suspend fun getmyweather(@Query("q") string: String):Response<WeatherData>
}