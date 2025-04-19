package com.alihafez.search.domain.repo

import com.alihafez.core.data.local.database.WeatherEntity
import com.alihafez.core.domain.*
import com.alihafez.core.domain.model.WeatherModel
import kotlinx.coroutines.flow.Flow

/**
 * created by ِAli Mahmoud Abdelhafez on 18/4/25
 **/
interface SearchRepo {
    suspend fun getWeatherByCityName(cityName: String): Result<WeatherModel, DataError.Remote>
    suspend fun saveLocationToDatabase(weatherModel: WeatherModel)
    fun getCashedList(): Flow<List<WeatherModel>>
}