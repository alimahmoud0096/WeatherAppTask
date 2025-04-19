package com.alihafez.core.data.remote.dto


/**
 * created by ِAli Mahmoud Abdelhafez on 17/4/25
 **/
data class ForecastDto(
    val cod: Int? = null,
    val message: Int? = null,
    val cnt: Int? = null,
    val list: List<ForecastItem?>? = null,
)

data class ForecastItem(
    val dt: Int? = null,
    val main: Main? = null,
    val visibility: Int? = null,
    val weather: List<Weather?>? = null,
    val wind: Wind? = null,
    val clouds: Clouds? = null,
    val pop: Double? = null,
    val sys: ForecastSys? = null,
    val dt_txt: String? = null
)

data class ForecastSys(
    val pod: String? = null
)