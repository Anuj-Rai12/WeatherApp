package com.example.myretrofit.api

import com.example.myretrofit.uitls.Myhelperclass
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder().baseUrl(Myhelperclass.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
    val api:WeatherServices by lazy {
        retrofit.create(WeatherServices::class.java)
    }
}