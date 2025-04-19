package com.alihafez.search.data.datasource

import com.alihafez.core.data.local.database.WeatherEntity
import com.alihafez.core.domain.model.Coord
import com.alihafez.core.domain.model.Weather
import com.alihafez.core.domain.model.WeatherModel

/**
 * created by ِAli Mahmoud Abdelhafez on 18/4/25
 **/
fun WeatherModel.toWeatherEntity(): WeatherEntity {
    return WeatherEntity(
        cityName = this.name,
        weatherName = this.weather[0].main,
        visibility = this.visibility,
        humidity = this.main.humidity,
        pressure = this.main.pressure,
        tempMax = this.main.tempMax,
        tempMin = this.main.tempMin,
        weatherId = this.weather[0].id,
        windSpeed = this.wind.speed,
        temp = this.main.temp
    )
}

fun WeatherEntity.toWeatherModel(): WeatherModel {
    return WeatherModel(
        name = this.cityName,
        weather = listOf(
            Weather(
                main = this.weatherName,
                id = this.weatherId,
                description = "",
                icon = ""
            )
        ),
        visibility = this.visibility,
        main = com.alihafez.core.domain.model.Main(
            humidity = this.humidity,
            pressure = this.pressure,
            tempMax = this.tempMax,
            tempMin = this.tempMin,
            seaLevel = 0,
            grndLevel = 0,
            feelsLike = 0.0,
            temp = this.temp
        ),
        wind = com.alihafez.core.domain.model.Wind(speed = this.windSpeed, deg = 0, gust = 0.0),
        cod = 0,
        coord = Coord(0.0, 0.0),
        base = "",
        id = 0,
        timezone = 0
    )
}