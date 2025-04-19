package com.alihafez.home.data.repoImpl

import com.alihafez.core.data.local.LocationData
import com.alihafez.core.data.local.LocationDataSource
import com.alihafez.core.domain.DataError
import com.alihafez.core.domain.Result
import com.alihafez.core.domain.map
import com.alihafez.home.data.datasource.remote.RemoteDataSource
import com.alihafez.core.data.remote.dto.toWeatherDomainModel
import com.alihafez.home.domain.model.ForecastModel
import com.alihafez.core.domain.model.WeatherModel
import com.alihafez.home.data.toForecastDomainModel
import com.alihafez.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * created by ِAli Mahmoud Abdelhafez on 18/4/25
 **/
class HomeRepoImp @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val locationDataSource: LocationDataSource
) : HomeRepository {

    override suspend fun getCurrentWeatherData(
        lat: Double,
        lon: Double
    ): Result<WeatherModel, DataError.Remote> {
        return remoteDataSource.getCurrentWeather(lat, lon).map { it.toWeatherDomainModel() }
    }

    override suspend fun getForecastData(
        lat: Double,
        lon: Double
    ): Result<ForecastModel, DataError.Remote> {
        return remoteDataSource.getForecast(lat, lon).map { it.toForecastDomainModel() }
    }

    override fun getLocation(): Flow<LocationData> {
        return locationDataSource.getLocation()
    }
}