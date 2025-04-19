package com.alihafez.home.domain.repository

import com.alihafez.core.data.local.LocationData
import com.alihafez.core.domain.DataError
import com.alihafez.core.domain.Result
import com.alihafez.home.domain.model.ForecastModel
import com.alihafez.core.domain.model.WeatherModel
import kotlinx.coroutines.flow.Flow

/**
 * created by ِAli Mahmoud Abdelhafez on 18/4/25
 **/
interface HomeRepository {
    suspend fun getCurrentWeatherData(lat: Double, lon: Double): Result<WeatherModel, DataError.Remote>
    suspend fun getForecastData(lat: Double, lon: Double): Result<ForecastModel, DataError.Remote>
    fun getLocation():Flow<LocationData>
}