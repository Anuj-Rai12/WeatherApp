package com.example.myretrofit

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.myretrofit.api.RetrofitInstance
import com.example.myretrofit.databinding.ActivityMainBinding
import com.example.myretrofit.model.WeatherData
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val topAnimation by lazy {
        AnimationUtils.loadAnimation(this, R.anim.top_anim)
    }
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)

        val  myinstance : LiveData<Response<WeatherData>> = liveData{
            val oop=RetrofitInstance.api.getmyweather("Ballia")
            emit(oop)
        }
        myinstance.observe(this, {
            if (it.isSuccessful) {
                //textin.append("The Data Are some ${it.body()?.weather?.iterator().}")
                it.body()?.weather?.iterator()?.forEach { itp ->
                    Toast.makeText(this, itp.description, Toast.LENGTH_SHORT).show()
                    //textin.append("this ")
                }
                //     textin.append("\n The temp -> ${it.body()?.main?.temp}")
            } else
                Toast.makeText(this, it.message(), Toast.LENGTH_SHORT).show()

        })
        setDemo()
    }
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun setDemo()
    {
        CoroutineScope(Main).launch {
            delay(10000)
            binding.mybackground.setImageDrawable(resources.getDrawable(R.drawable.clearsky,null))
            binding.relevi.startAnimation(topAnimation)
            binding.secretiv.startAnimation(topAnimation)
        }
    }
}