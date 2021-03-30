package com.example.myretrofit

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.SearchView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.example.myretrofit.databinding.ActivityMainBinding
import com.example.myretrofit.mycontrol.MyViewFactory
import com.example.myretrofit.mycontrol.MyViewModel
import com.example.myretrofit.repos.Repository
import com.example.myretrofit.uitls.Myhelperclass
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var myViewModel: MyViewModel
    private lateinit var myViewFactory: MyViewFactory
    private val repository by lazy {
        Repository()
    }
    private val fadeAnimation by lazy {
        AnimationUtils.loadAnimation(this, R.anim.fade_in)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        myViewModelFun()
        setmydata()
        myViewModel._snackbar.observe(this, {
            it.getContentIfNotHandled()
                ?.let {
                    Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                    if (it == "Data Downloaded Successfully") {
                        binding.clec.visibility = View.VISIBLE
                        binding.yu.visibility = View.VISIBLE
                        binding.secretiv.visibility = View.VISIBLE
                    }
                }
        })
        binding.myserchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    //  myViewModel.inital()
                    setmydata(query)
                    return true
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        binding.lifecycleOwner = this
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun setDemo(string: String) {
        CoroutineScope(Main).launch {
            if (getBitmap(string) != null)
                binding.mybackground.setImageBitmap(getBitmap(string))
            else
                binding.mybackground.setImageDrawable(
                    resources.getDrawable(
                        R.drawable.clearsky,
                        null
                    )
                )
            binding.clec.visibility = View.VISIBLE
            binding.yu.visibility = View.VISIBLE
            binding.secretiv.visibility = View.VISIBLE
            binding.relevi.startAnimation(fadeAnimation)
            binding.secretiv.startAnimation(fadeAnimation)
        }
    }

    private fun myViewModelFun() {
        myViewFactory = MyViewFactory(repository)
        myViewModel = ViewModelProvider(this, myViewFactory).get(MyViewModel::class.java)
        binding.myvarible = myViewModel
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setmydata(string: String = "Ballia") {
        myViewModel.getsMyData(string).observe(this, {
            if (it.isSuccessful) {
                var desc: String = "Not Data Found Main"
                it.body()?.weather?.iterator()?.forEach { op ->
                    desc = op.description
                    Myhelperclass.myIcons[op.icon]?.let { str -> setDemo(str) }
                }
                myViewModel.setData(it, desc)
            } else {
                Toast.makeText(
                    this,
                    "No Data Found\nOr\nTry to give some Space between your Search Query ",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }


    private suspend fun getBitmap(string: String): Bitmap? {
        binding.myprogress.visibility = View.VISIBLE
        val loading = ImageLoader(this)
        val request = ImageRequest.Builder(this)
            .data(string)
            .build()
        return try {
            val result = (loading.execute(request) as SuccessResult).drawable
            binding.myprogress.visibility = View.GONE
            (result as BitmapDrawable).bitmap
        } catch (e: Exception) {
            binding.myprogress.visibility = View.GONE
            Toast.makeText(this, "Check Your Internet Connection", Toast.LENGTH_SHORT).show()
            null
        }
    }
}