package com.example.myretrofit.uitls

import com.example.myretrofit.R

class Myhelperclass {
    companion object {
        const val BaseUrl = "https://api.openweathermap.org/data/2.5/"
        const val TableName="Location_Table"
        val myIcons = mapOf(
            "01d" to "https://images.unsplash.com/photo-1516571999955-7ef6c7885017?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=634&q=80",
            "02d" to "https://images.unsplash.com/photo-1613090956335-e9330f8ff73d?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=634&q=80",
            "03d" to "https://images.unsplash.com/photo-1601992664212-8f591f72733b?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=334&q=80",
            "04d" to "https://images.unsplash.com/photo-1606574819936-14d39cbc715f?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=675&q=80",
            "09d" to "https://images.unsplash.com/photo-1559660312-f63824b09080?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=282&q=80",
            "10d" to "https://images.unsplash.com/photo-1534274988757-a28bf1a57c17?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=675&q=80",
            "11d" to "https://cdn.britannica.com/62/158162-050-9FDE49B4/thunderstorm-and-lightning.jpg",
            "13d" to "https://images.unsplash.com/photo-1478265409131-1f65c88f965c?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=675&q=80",
            "50d" to "https://images.unsplash.com/photo-1485236715568-ddc5ee6ca227?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=633&q=80",
            "01n" to "https://images.unsplash.com/photo-1513628253939-010e64ac66cd?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=634&q=80",
            "02n" to "https://images.unsplash.com/photo-1592842825197-89dd8efba31f?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=634&q=80",
            "03n" to "https://qph.fs.quoracdn.net/main-qimg-506bee5948e08eb9100f86cd17f6314f.webp",
            "04n" to "https://i.pinimg.com/originals/8b/98/88/8b98886a5cc8f37bafd8654193e06187.jpg",
            "09n" to "https://ak.picdn.net/shutterstock/videos/1058460370/thumb/1.jpg",
            "10n" to "https://images.unsplash.com/photo-1534274988757-a28bf1a57c17?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=675&q=80",
            "11n" to "https://images.unsplash.com/photo-1564343921985-91ced954364a?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=1050&q=80",
            "13n" to "https://iphonewalls.net/wp-content/uploads/2014/11/Snow%20Park%20Bench%20Light%20Pole%20iPhone%206%20Wallpaper-320x480.jpg",
            "50n" to "https://images.unsplash.com/photo-1611439302326-3279b5e31829?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=634&q=80"
        )
        val notificationIcons = mapOf(
            "01d" to R.mipmap.day,
            "02d" to R.mipmap.sunwithclouds,
            "03d" to R.mipmap.clouds,
            "04d" to R.mipmap.brokenclouds,
            "09d" to R.mipmap.showerain,
            "10d" to R.mipmap.rainday,
            "11d" to R.mipmap.thunderstrom,
            "13d" to R.mipmap.snow,
            "50d" to R.mipmap.mist,
            "01n" to R.mipmap.night,
            "02n" to R.mipmap.nightwithclouds,
            "03n" to R.mipmap.clouds,
            "04n" to R.mipmap.brokenclouds,
            "09n" to R.mipmap.showerain,
            "10n" to R.mipmap.nightrain,
            "11n" to R.mipmap.thunderstrom,
            "13n" to R.mipmap.snow,
            "50n" to R.mipmap.mist
            )
    }
}