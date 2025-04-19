package com.alihafez.home.data

import com.alihafez.core.data.remote.dto.Clouds
import com.alihafez.core.data.remote.dto.ForecastDto
import com.alihafez.core.data.remote.dto.ForecastSys
import com.alihafez.home.domain.model.ForecastModel
import com.alihafez.home.domain.model.ForecastModelItem
import com.alihafez.core.domain.model.Main
import com.alihafez.core.domain.model.Weather
import com.alihafez.core.domain.model.Wind

/**
 * created by ِAli Mahmoud Abdelhafez on 18/4/25
 **/
fun ForecastDto.toForecastDomainModel(): ForecastModel {
    return ForecastModel(
        cod = cod ?: 0,
        message = message ?: 0,
        cnt = cnt ?: 0,
        list = list?.map {
            ForecastModelItem(
                dt = it?.dt ?: 0,
                main = Main(
                    feelsLike = it?.main?.feels_like ?: 0.0,
                    grndLevel = it?.main?.grnd_level ?: 0,
                    humidity = it?.main?.humidity ?: 0,
                    pressure = it?.main?.pressure ?: 0,
                    seaLevel = it?.main?.sea_level ?: 0,
                    temp = it?.main?.temp ?: 0.0,
                    tempMax = it?.main?.temp_max ?: 0.0,
                    tempMin = it?.main?.temp_min ?: 0.0
                ),
                visibility = it?.visibility ?: 0,
                weather = it?.weather?.map { weather ->
                    Weather(
                        description = weather?.description ?: "",
                        icon = weather?.icon ?: "",
                        id = weather?.id ?: 0,
                        main = weather?.main ?: ""
                    )
                } ?: emptyList(),
                wind = Wind(
                    deg = it?.wind?.deg ?: 0,
                    gust = it?.wind?.gust ?: 0.0,
                    speed = it?.wind?.speed ?: 0.0
                ),
                clouds = Clouds(
                    all = it?.clouds?.all ?: 0
                ),
                pop = it?.pop ?: 0.0,
                sys = ForecastSys(
                    pod = it?.sys?.pod ?: ""
                ),
                dtTxt = it?.dt_txt ?: "",
                hourlyForecast = emptyList()
            )
        } ?: emptyList()
    )
}