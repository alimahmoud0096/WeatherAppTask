package com.alihafez.home.domain.model

import androidx.annotation.DrawableRes
import com.alihafez.core.presentation.common.formatTime
import com.alihafez.core.presentation.getWeatherIconBasedOnId
import com.alihafez.core.data.remote.dto.Clouds
import com.alihafez.core.data.remote.dto.ForecastSys
import com.alihafez.core.domain.model.Main
import com.alihafez.core.domain.model.Weather
import com.alihafez.core.domain.model.Wind

/**
 * created by ِAli Mahmoud Abdelhafez on 18/4/25
 **/
data class ForecastModel(
    val cod: Int,
    val message: Int,
    val cnt: Int,
    val list: List<ForecastModelItem>
)

data class ForecastModelItem(
    val dt: Int,
    val main: Main,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind,
    val clouds: Clouds,
    val pop: Double,
    val sys: ForecastSys,
    val dtTxt: String,
    val hourlyForecast: List<HourlyForecast?>
)

data class HourlyForecast(
    val weatherId: Int,
    val main: Main,
    val hour: String
) {

    @get:DrawableRes
    val weatherIcon: Int get() = getWeatherIconBasedOnId(weatherId)
}


fun ForecastModelItem.asHourlyForecast(): HourlyForecast {
    return HourlyForecast(
        weatherId = weather[0].id,
        main = Main(
            feelsLike = main.feelsLike,
            grndLevel = main.grndLevel,
            humidity = main.humidity,
            pressure = main.pressure,
            seaLevel = main.seaLevel,
            temp = main.temp,
            tempMax = main.tempMax,
            tempMin = main.tempMin
        ),
        hour = dtTxt.formatTime()
    )
}