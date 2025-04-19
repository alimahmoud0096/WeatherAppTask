package com.alihafez.core.data.remote

import com.alihafez.core.data.remote.dto.ForecastDto
import com.alihafez.core.data.remote.dto.WeatherDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * created by ِAli Mahmoud Abdelhafez on 17/4/25
 **/
interface WeatherApiService {

    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): Response<WeatherDto>

    @GET("forecast")
    suspend fun getForecast(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): Response<ForecastDto>

    @GET("weather")
    suspend fun getWeatherByCityName(
        @Query("q") cityName: String,
    ): Response<WeatherDto>

    companion object {
        const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    }
}