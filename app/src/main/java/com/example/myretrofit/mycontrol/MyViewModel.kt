package com.example.myretrofit.mycontrol

import androidx.lifecycle.*
import com.example.myretrofit.model.WeatherData
import com.example.myretrofit.repos.Repository
import com.example.myretrofit.room.RoomData
import com.example.myretrofit.uitls.Event
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Response
import kotlin.math.roundToInt

class MyViewModel(private val repository: Repository) :ViewModel() {
//Get all the data base
    val allData=repository.daoAll
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
    fun insertLocation(cityname: String, flags: Boolean, obj: RoomData?){
        if (!flags)//false
        {
            getLocation(RoomData(0, cityname, 0))
            _snackbar.value = Event("City Is Added Successfully")
        }
        else{
            if (obj!=null) {
                updateLocation(RoomData(obj.id, cityname, obj.per))
                _snackbar.value = Event("Updated the location,successfully")
            }
        }
    }
    fun deletAll(boolean: Boolean,obj: RoomData?)
    {
        if (!boolean)
        {
        deleteAll()
        _snackbar.value= Event("Your Location is Deleted")
            return
        }
        else
        {
            obj?.let {
                deleteOneLocation(it)
                _snackbar.value= Event("Record is Deleted")
            }
        }
    }
        fun updateAuto(obj: RoomData)
        {
            updateLocation(RoomData(obj.id,obj.location_name,1))
            _snackbar.value=Event("ok")
        }
    private fun degree(kelvin: Double): String {
        return (kelvin - 273.15f).roundToInt().toString()
    }
    private fun getLocation(roomData: RoomData):Job=viewModelScope.launch{
        repository.getLocation(roomData)
    }
    private fun updateLocation(roomData: RoomData):Job=viewModelScope.launch {
        repository.updateLocation(roomData)
    }
    private fun deleteOneLocation(roomData: RoomData):Job=viewModelScope.launch {
        repository.deleteLocation(roomData)
    }
    private fun deleteAll():Job=viewModelScope.launch {
        repository.deleteAllLocation()
    }
}