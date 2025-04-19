package com.alihafez.search.data.datasource.remote

import com.alihafez.core.data.remote.dto.WeatherDto
import com.alihafez.core.domain.*

/**
 * created by ِAli Mahmoud Abdelhafez on 18/4/25
 **/
interface RemoteDataSource {
    suspend fun getWeatherByCityName(cityName: String): Result<WeatherDto, DataError.Remote>
}