package com.example.myretrofit

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import com.example.myretrofit.databinding.ActivityMain2Binding
import com.example.myretrofit.uitls.Myhelperclass
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    private val fadeAnimation by lazy {
        AnimationUtils.loadAnimation(this, R.anim.fade_in)
    }
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main2)
        supportActionBar?.hide()
        val array = arrayListOf(
            "01d",
            "01n",
            "02n",
            "03d",
            "04d",
            "09d",
            "10d",
            "03n",
            "09n",
            "11n"
        )
        CoroutineScope(Main).launch{
            array.forEach { io ->
                binding.imageView.setImageDrawable(
                    resources
                        .getDrawable(Myhelperclass.notificationIcons[io]!!, null)
                )
                delay(900)
                binding.imageView.startAnimation(fadeAnimation)
            }
            val intent=Intent(this@MainActivity2,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}