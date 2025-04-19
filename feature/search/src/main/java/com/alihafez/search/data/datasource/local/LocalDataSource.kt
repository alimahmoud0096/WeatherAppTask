package com.alihafez.search.data.datasource.local

import com.alihafez.core.data.local.database.WeatherEntity
import kotlinx.coroutines.flow.Flow

/**
 * created by ِAli Mahmoud Abdelhafez on 18/4/25
 **/
interface LocalDataSource {
    suspend fun saveLocationToDatabase(weatherEntity: WeatherEntity)
    fun getCashedList(): Flow<List<WeatherEntity>>
}