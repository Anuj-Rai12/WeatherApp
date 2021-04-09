package com.example.myretrofit.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myretrofit.uitls.Myhelperclass

@Database(entities = [RoomData::class], version = 1)
abstract class RoomDataInstance : RoomDatabase() {
    abstract val daoAccessObj: MyDao

    companion object {
        private var INSTANCE: RoomDataInstance? = null
        fun getInstance(context: Context): RoomDataInstance {
            synchronized(this) {
                var instance = INSTANCE
                if (instance != null) {
                    return instance
                }
                synchronized(this) {
                    val oldinstance = Room.databaseBuilder(
                        context.applicationContext,
                        RoomDataInstance::class.java,
                        "${Myhelperclass.TableName}"
                    ).build()
                    INSTANCE = oldinstance
                    return oldinstance
                }
            }

        }
    }
}