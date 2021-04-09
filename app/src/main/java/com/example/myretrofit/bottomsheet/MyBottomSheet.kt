package com.example.myretrofit.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.isDigitsOnly
import androidx.databinding.DataBindingUtil
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
        binding = DataBindingUtil.inflate(inflater, R.layout.mybottomsheet, container, false)
//        binding.mybottonsheet.setBackgroundColor(resources.getColor(R.color.chreeyred,null))
        binding.createloc.setOnClickListener {
            validateRes(binding.mycity.text.toString())?.let {
                myHelperInter?.sendData(it)
                dismiss()
            }
            myHelperInter?.sendData("Enter the correct option")
        }
        return binding.root
    }

    private fun validateRes(string: String?): String? =
        if (string.isNullOrBlank() || string.isDigitsOnly())
            null
        else
            string

}