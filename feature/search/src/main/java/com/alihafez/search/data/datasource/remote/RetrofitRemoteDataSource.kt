package com.alihafez.search.data.datasource.remote

import com.alihafez.core.data.remote.WeatherApiService
import com.alihafez.core.data.remote.dto.WeatherDto
import com.alihafez.core.data.remote.safeCall
import com.alihafez.core.domain.DataError
import com.alihafez.core.domain.Result
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * created by ِAli Mahmoud Abdelhafez on 18/4/25
 **/
class RetrofitRemoteDataSource @Inject constructor(private val apiService: WeatherApiService) :
    RemoteDataSource {
    override suspend fun getWeatherByCityName(cityName: String): Result<WeatherDto, DataError.Remote> {
        return safeCall { apiService.getWeatherByCityName(cityName) }
    }

}