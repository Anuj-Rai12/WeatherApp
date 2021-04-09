package com.example.myretrofit.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  addLocation(roomData: RoomData)

    @Update
    suspend fun updateLocation(roomData: RoomData)

    @Delete
    suspend fun deleteLocation(roomData: RoomData)

    @Query("Select *from Location_Table")
     fun displayLocation():LiveData<List<RoomData>>

    @Query("delete from Location_Table")
        suspend fun deleteAll()
}