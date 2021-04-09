package com.example.myretrofit.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myretrofit.uitls.Myhelperclass

@Entity(tableName = Myhelperclass.TableName)
data class RoomData(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Location_Id")
    var id:Int,
    @ColumnInfo(name = "Location_Name")
    var location_name:String,
    @ColumnInfo(name="Location_permission")
    var per:Int
)
