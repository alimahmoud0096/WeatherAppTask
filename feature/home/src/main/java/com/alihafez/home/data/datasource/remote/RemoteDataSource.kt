package com.alihafez.home.data.datasource.remote

import com.alihafez.core.domain.*
import com.alihafez.core.data.remote.dto.ForecastDto
import com.alihafez.core.data.remote.dto.WeatherDto

/**
 * created by ِAli Mahmoud Abdelhafez on 18/4/25
 **/
interface RemoteDataSource {
    suspend fun getCurrentWeather(lat: Double, lon: Double): Result<WeatherDto, DataError.Remote>
    suspend fun getForecast(lat: Double, lon: Double): Result<ForecastDto, DataError.Remote>

}