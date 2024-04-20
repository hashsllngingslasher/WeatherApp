package com.example.weatherapp

data class WeatherModel (
    val name: String,
    val weather: List<WeatherDetail>,
    val main: WeatherMain
)

data class WeatherDetail(
    val main: String,
)

data class WeatherMain(
    val temp: Double,
    val humidity: Int
)