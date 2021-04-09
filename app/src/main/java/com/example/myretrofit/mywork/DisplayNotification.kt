package com.example.myretrofit.mywork

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.myretrofit.MainActivity
import com.example.myretrofit.uitls.Myhelperclass


class DisplayNotification(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {


    private var notificationManager: NotificationManager? = null
    private val channelId = "com.example.myretrofit.mywork"
    private var todayWeather:String?=null
    override fun doWork(): Result {
        notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val getDesc = inputData.getString("wedescp").toString()
        val geTemp = inputData.getString("wetemp").toString()
        val getIcon = inputData.getString("weicon").toString()
        return try {
            todayWeather = geTemp
            todayWeather?.let {
                createNotification(it, getDesc, getIcon)
            }
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }

    private fun createNotification(
        name: String,
        desc: String = "No data Found",
        getIcon: String
    ) {
        val notificationId = 23
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(channelId, name, NotificationManager.IMPORTANCE_HIGH).apply {
                    description = desc
                }
            notificationManager?.createNotificationChannel(channel)
        }
        val intent = Intent(applicationContext, MainActivity::class.java)
        val pendingIntent = TaskStackBuilder.create(applicationContext).run {
            addNextIntentWithParentStack(intent)
            getPendingIntent(101, PendingIntent.FLAG_UPDATE_CURRENT)
        }
        val notification = Myhelperclass.notificationIcons[getIcon]?.let {
            NotificationCompat.Builder(applicationContext, channelId)
                .setContentTitle(name)
                .setContentText(desc)
                .setAutoCancel(true)
                .setLargeIcon(MainActivity.bitmapval)
                .setContentIntent(pendingIntent)
                .setSmallIcon(it)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .build()
        }
        notificationManager?.notify(notificationId, notification)
    }
}