package com.example.myretrofit.bottomsheet

import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.isDigitsOnly
import androidx.databinding.DataBindingUtil
import com.example.myretrofit.MainActivity
import com.example.myretrofit.R
import com.example.myretrofit.databinding.MybottomsheetBinding
import com.example.myretrofit.uitls.MyHelperInter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MyBottomSheet : BottomSheetDialogFragment() {
     private lateinit var binding: MybottomsheetBinding
    var myHelperInter: MyHelperInter? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        binding = DataBindingUtil.inflate(inflater, R.layout.mybottomsheet, container, false)
        MainActivity.cityname?.let {
            binding.mybottonsheettitile.text="Your CityName"
            binding.mycity.setText(it)
            binding.createloc.text="Update"
//            binding.deleteLoc.visibility=View.VISIBLE
        }
        binding.createloc.setOnClickListener {
            validateRes(binding.mycity.text.toString())?.let {
                if(MainActivity.cityname!=null)
                myHelperInter?.sendData(it,true)//update walla
                else
                myHelperInter?.sendData(it,false)//insert walla
                dismiss()
            }
        }
        return binding.root
    }

    private fun validateRes(string: String?): String? =
        if (string.isNullOrBlank() || string.isDigitsOnly())
            null
        else
            string

}