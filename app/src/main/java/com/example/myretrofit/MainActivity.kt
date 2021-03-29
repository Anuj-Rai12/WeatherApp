package com.example.myretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.myretrofit.api.RetrofitInstance
import com.example.myretrofit.model.WeatherData
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textin:TextView=findViewById(R.id.myfit)
        val  myinstance : LiveData<Response<WeatherData>> = liveData{
            val oop=RetrofitInstance.api.getmyweather("Ballia")
            emit(oop)
        }
        myinstance.observe(this, {
            if (it.isSuccessful) {
                //textin.append("The Data Are some ${it.body()?.weather?.iterator().}")
                it.body()?.weather?.iterator()?.forEach { itp ->
                    textin.text = itp.description
                    //textin.append("this ")
                }
                textin.append("\n The temp -> ${it.body()?.main?.temp}")
            }
            else
                textin.text=it.message().toString()

        })
    }
}