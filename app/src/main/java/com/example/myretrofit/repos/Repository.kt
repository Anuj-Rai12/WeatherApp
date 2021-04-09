package com.example.myretrofit.repos

import com.example.myretrofit.api.RetrofitInstance
import com.example.myretrofit.model.WeatherData
import com.example.myretrofit.room.MyDao
import com.example.myretrofit.room.RoomData
import retrofit2.Response

class Repository(private val myDao: MyDao) {
    //Retrofit Database
    suspend fun getMyData(string: String): Response<WeatherData> =
        RetrofitInstance.api.getmyweather(string)

    //Room database
    val daoAll = myDao.displayLocation()

    suspend fun getLocation(roomData: RoomData) {
        myDao.addLocation(roomData)
    }

    suspend fun updateLocation(roomData: RoomData): Unit = myDao.updateLocation(roomData)

    suspend fun deleteLocation(roomData: RoomData): Unit = myDao.deleteLocation(roomData)

    suspend fun deleteAllLocation(): Unit = myDao.deleteAll()

}