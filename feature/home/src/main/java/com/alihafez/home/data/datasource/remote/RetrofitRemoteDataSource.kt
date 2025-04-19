package com.alihafez.home.data.datasource.remote

import com.alihafez.core.data.remote.WeatherApiService
import com.alihafez.core.data.remote.safeCall
import com.alihafez.core.domain.DataError
import com.alihafez.core.domain.Result
import com.alihafez.core.data.remote.dto.ForecastDto
import com.alihafez.core.data.remote.dto.WeatherDto
import javax.inject.Inject

/**
 * created by ِAli Mahmoud Abdelhafez on 17/4/25
 **/
class RetrofitRemoteDataSource @Inject constructor(private val apiService: WeatherApiService) :
    RemoteDataSource {

    override suspend fun getCurrentWeather(
        lat: Double,
        lon: Double
    ): Result<WeatherDto, DataError.Remote> {
        return safeCall {
            apiService.getCurrentWeather(lat, lon)
        }
    }

    override suspend fun getForecast(
        lat: Double,
        lon: Double
    ): Result<ForecastDto, DataError.Remote> {
        return safeCall {
            apiService.getForecast(lat, lon)
        }
    }
}