package com.alihafez.core.domain.model

/**
 * created by ِAli Mahmoud Abdelhafez on 18/4/25
 **/
data class WeatherModel(
    val base: String,
    val cod: Int,
    val coord: Coord,
    val id: Int,
    val main: Main,
    val name: String,
    val timezone: Int,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
)

data class Coord(
    val lat: Double,
    val lon: Double
)

data class Main(
    val feelsLike: Double,
    val grndLevel: Int,
    val humidity: Int,
    val pressure: Int,
    val seaLevel: Int,
    val temp: Double,
    val tempMax: Double,
    val tempMin: Double
)


data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)

data class Wind(
    val deg: Int,
    val gust: Double,
    val speed: Double
)

