package com.example.myretrofit.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response

class Inpector :Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val request=chain.request()
            .newBuilder()
            .get()
            .addHeader("app id","3d8818169ab996c8215ac7e5c5f165b")
            .build()
        return chain.proceed(request)
    }
}