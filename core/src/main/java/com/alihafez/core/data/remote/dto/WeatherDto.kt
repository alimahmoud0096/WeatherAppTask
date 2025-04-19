package com.alihafez.core.data.remote.dto

import com.alihafez.core.domain.model.WeatherModel


/**
 * created by ِAli Mahmoud Abdelhafez on 18/4/25
 **/
data class WeatherDto(
    val base: String? = null,
    val clouds: Clouds? = null,
    val cod: Int? = null,
    val coord: Coord? = null,
    val dt: Int? = null,
    val id: Int? = null,
    val main: Main? = null,
    val name: String? = null,
    val sys: Sys? = null,
    val timezone: Int? = null,
    val visibility: Int? = null,
    val weather: List<Weather?>? = null,
    val wind: Wind? = null)

data class Clouds(
    val all: Int? = null
)

data class Coord(
    val lat: Double? = null,
    val lon: Double? = null
)

data class Main(
    val feels_like: Double? = null,
    val grnd_level: Int? = null,
    val humidity: Int? = null,
    val pressure: Int? = null,
    val sea_level: Int? = null,
    val temp: Double? = null,
    val temp_max: Double? = null,
    val temp_min: Double? = null
)

data class Sys(
    val country: String? = null,
    val id: Int? = null,
    val sunrise: Int? = null,
    val sunset: Int? = null,
    val type: Int? = null
)

data class Weather(
    val description: String? = null,
    val icon: String? = null,
    val id: Int? = null,
    val main: String? = null
)

data class Wind(
    val deg: Int? = null,
    val gust: Double? = null,
    val speed: Double? = null
)

fun WeatherDto.toWeatherDomainModel(): WeatherModel =
    WeatherModel(
        base = base ?: "",
        cod = cod ?: 0,
        coord = com.alihafez.core.domain.model.Coord(
            lat = coord?.lat ?: 0.0,
            lon = coord?.lon ?: 0.0
        ),
        id = id ?: 0,
        main = com.alihafez.core.domain.model.Main(
            feelsLike = main?.feels_like ?: 0.0,
            grndLevel = main?.grnd_level ?: 0,
            humidity = main?.humidity ?: 0,
            pressure = main?.pressure ?: 0,
            seaLevel = main?.sea_level ?: 0,
            temp = main?.temp ?: 0.0,
            tempMax = main?.temp_max ?: 0.0,
            tempMin = main?.temp_min ?: 0.0
        ),
        name = name ?: "",
        timezone = timezone ?: 0,
        visibility = visibility ?: 0,
        weather = weather?.map {
            com.alihafez.core.domain.model.Weather(
                description = it?.description ?: "",
                icon = it?.icon ?: "",
                id = it?.id ?: 0,
                main = it?.main ?: ""
            )
        } ?: emptyList(),
        wind = com.alihafez.core.domain.model.Wind(
            deg = wind?.deg ?: 0,
            gust = wind?.gust ?: 0.0,
            speed = wind?.speed ?: 0.0
        )

    )
