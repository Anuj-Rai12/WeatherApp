package com.example.myretrofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main2)
        supportActionBar?.hide()
        val array = arrayListOf(
            "01d",
            "02d",
            "03d",
            "04d",
            "09d",
            "10d",
            "03n",
            "04n",
            "09n",
            "10n",
            "11n",
            "13n"
        )
        CoroutineScope(Main).launch{
            array.forEach { io ->
                binding.imageView.setImageDrawable(
                    resources
                        .getDrawable(Myhelperclass.notificationIcons[io]!!, null)
                )
                delay(800)
                binding.imageView.startAnimation(fadeAnimation)
            }
            delay(3000)
            val intent=Intent(this@MainActivity2,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}