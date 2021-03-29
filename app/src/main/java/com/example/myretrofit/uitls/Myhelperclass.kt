package com.example.myretrofit.uitls

class Myhelperclass {
    companion object {
        const val BaseUrl="https://api.openweathermap.org/data/2.5/"
        val todaysday = mapOf(
            "01d" to "clearday",
            "02d" to "few cloud's",
            "03d" to "scattered clouds",
            "04d" to "broken clouds",
            "09d" to "shower rain",
            "10d" to "rain",
            "11d" to "thunderstorm",
            "13d" to "snow",
            "50d" to "mist"
        )
        val todaysnight = mapOf(
            "01n" to "clearday",
            "02n" to "few cloud's",
            "03n" to "scattered clouds",
            "04n" to "broken clouds",
            "09n" to "shower rain",
            "10n" to "rain",
            "11n" to "thunderstorm",
            "13n" to "snow",
            "50n" to "mist"
        )

    }
}