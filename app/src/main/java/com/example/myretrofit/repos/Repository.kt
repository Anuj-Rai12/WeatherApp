package com.example.myretrofit.repos

import com.example.myretrofit.api.RetrofitInstance
import com.example.myretrofit.model.WeatherData
import retrofit2.Response

class Repository {
    suspend fun getMyData(string: String): Response<WeatherData> =
        RetrofitInstance.api.getmyweather(string)
}