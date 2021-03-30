package com.example.myretrofit.mycontrol

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.myretrofit.model.WeatherData
import com.example.myretrofit.repos.Repository
import com.example.myretrofit.uitls.Event
import retrofit2.Response
import kotlin.math.roundToInt

class MyViewModel(private val repository: Repository) :ViewModel() {

    val temp = MutableLiveData<String>()

    var desc = MutableLiveData<String>()

    val maxTemp = MutableLiveData<String>()

    val minTemp = MutableLiveData<String>()

    val press = MutableLiveData<String>()

    val Humid = MutableLiveData<String>()

    val windspeed = MutableLiveData<String>()

    val GustWind = MutableLiveData<String>()

    val countryname = MutableLiveData<String>()

    val cityname = MutableLiveData<String>()

    val lon = MutableLiveData<String>()

    val lati = MutableLiveData<String>()

    val _snackbar=MutableLiveData<Event<String>>()

    init {
        desc.value = "No Data Found"
    }




    fun setData(response: Response<WeatherData>, desc: String)
    {
        if (!response.isSuccessful)
        {
            _snackbar.value= Event("No data Found")
            return
        }
        temp.value = response.body()?.main?.let { degree(it.temp) }
        this.desc.value="   "+desc.toUpperCase()
        maxTemp.value ="Temp Max :"+ response.body()?.main?.let { degree(it.tempMax) }
        minTemp.value ="Temp Min :"+ response.body()?.main?.let { degree(it.tempMin) }
        press.value = "Pressure    :"+response.body()?.main?.pressure?.toString()
        Humid.value ="Humidity    :"+ response.body()?.main?.humidity?.toString()
        windspeed.value="Wind Speed:"+response.body()?.wind?.speed.toString()
        GustWind.value="Gust Wind   :"+response.body()?.wind?.gust.toString()
        countryname.value=" Country :"+response.body()?.sys?.country
        cityname.value="City :"+response.body()?.name
        lon.value="Longitude:"+response.body()?.coord?.lon.toString()
        lati.value="Latitude:"+response.body()?.coord?.lat.toString()
        _snackbar.value= Event("Data Downloaded Successfully")
    }
    /*    val myinstance: LiveData<Response<WeatherData>> = liveData {
        val oop = RetrofitInstance.api.getmyweather("Patna")
        emit(oop)
    }*/
/*fun inital()
    {
        temp.value = "-0"
        desc.value = "not data found 😥"
        maxTemp.value = ""
        minTemp.value = ""
        press.value = ""
        Humid.value = ""
        windspeed.value = ""
        GustWind.value = ""
        countryname.value = " "
        cityname.value = ""
        lon.value = ""
        lati.value = ""
}*/
    fun getsMyData(string: String):LiveData<Response<WeatherData>> {
        return  liveData {
            try {
                val oop = repository.getMyData(string)
                emit(oop)
            }
            catch (e: Exception)
            {
                _snackbar.value= Event("Check Your Internet")
            }
        }
    }
    private fun degree(kelvin: Double): String {
        return (kelvin - 273.15f).roundToInt().toString()
    }
}