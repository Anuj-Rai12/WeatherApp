package com.example.myretrofit.uitls

import android.content.Intent

interface MyHelperInter {
    fun callStart(intent: Intent?)
    fun sendData(string: String)
}