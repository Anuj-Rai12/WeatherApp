package com.example.myretrofit.mycontrol

import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myretrofit.repos.Repository

class MyViewFactory(private val repository: Repository) :ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MyViewModel(repository) as T
    }
}