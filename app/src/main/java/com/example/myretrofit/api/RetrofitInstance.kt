package com.example.myretrofit.api

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val Baseurl="https://api.openweathermap.org/data/2.5/"

    private val retrofit by lazy {
        Retrofit.Builder().baseUrl(Baseurl)
      //      .client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
    val api:WeatherServices by lazy {
        retrofit.create(WeatherServices::class.java)
    }
}