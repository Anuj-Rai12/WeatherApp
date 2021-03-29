package com.example.myretrofit.api

import com.example.myretrofit.model.WeatherData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface  WeatherServices {
    @GET("weather")
    suspend fun getmyweather(@Query("q") string: String,
    @Query("appid") str:String="53d8818169ab996c8215ac7e5c5f165b"):Response<WeatherData>
}