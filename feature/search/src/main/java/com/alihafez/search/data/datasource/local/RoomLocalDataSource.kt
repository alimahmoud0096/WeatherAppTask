package com.alihafez.search.data.datasource.local

import com.alihafez.core.data.local.database.WeatherDao
import com.alihafez.core.data.local.database.WeatherEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * created by ِAli Mahmoud Abdelhafez on 18/4/25
 **/
class RoomLocalDataSource @Inject constructor(private val weatherDao: WeatherDao) :
    LocalDataSource {
    override suspend fun saveLocationToDatabase(weatherEntity: WeatherEntity) {
        weatherDao.insertWeather(weatherEntity)
    }

    override fun getCashedList(): Flow<List<WeatherEntity>> {
        return weatherDao.getAllWeather()
    }
}