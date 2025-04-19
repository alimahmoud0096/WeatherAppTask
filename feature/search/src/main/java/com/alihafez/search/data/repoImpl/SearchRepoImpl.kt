package com.alihafez.search.data.repoImpl

import com.alihafez.core.data.local.database.WeatherEntity
import com.alihafez.core.data.remote.dto.toWeatherDomainModel
import com.alihafez.core.domain.DataError
import com.alihafez.core.domain.Result
import com.alihafez.core.domain.map
import com.alihafez.core.domain.model.WeatherModel
import com.alihafez.search.data.datasource.local.LocalDataSource
import com.alihafez.search.data.datasource.remote.RemoteDataSource
import com.alihafez.search.data.datasource.toWeatherEntity
import com.alihafez.search.data.datasource.toWeatherModel
import com.alihafez.search.domain.repo.SearchRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * created by ِAli Mahmoud Abdelhafez on 18/4/25
 **/
class SearchRepoImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) :
    SearchRepo {
    override suspend fun getWeatherByCityName(cityName: String): Result<WeatherModel, DataError.Remote> {
        return remoteDataSource.getWeatherByCityName(cityName).map { it.toWeatherDomainModel() }
    }

    override suspend fun saveLocationToDatabase(weatherModel: WeatherModel) {
        localDataSource.saveLocationToDatabase(weatherModel.toWeatherEntity())

    }

    override fun getCashedList(): Flow<List<WeatherModel>> {
        return localDataSource.getCashedList().map {
            it.map { it.toWeatherModel() }
        }
    }
}