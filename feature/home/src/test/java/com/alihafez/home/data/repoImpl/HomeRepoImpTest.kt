package com.alihafez.home.data.repoImpl

import com.alihafez.core.data.local.LocationDataSource
import com.alihafez.core.data.remote.dto.toWeatherDomainModel
import com.alihafez.core.domain.DataError
import com.alihafez.core.domain.Result
import com.alihafez.home.data.datasource.remote.RemoteDataSource
import com.alihafez.home.data.fake.fakeForecastDto
import com.alihafez.home.data.fake.fakeWeatherDto
import com.alihafez.home.data.fake.fakeWeatherModel
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

/**
 * created by ِAli Mahmoud Abdelhafez on 18/4/25
 */
@RunWith(MockitoJUnitRunner.Silent::class)
class HomeRepoImpTest {

    @Mock
    private lateinit var remoteDataSource: RemoteDataSource

    @Mock
    private lateinit var locationDataSource: LocationDataSource

    private lateinit var SUT: HomeRepoImp

    @Before
    fun setup() {
        SUT = HomeRepoImp(remoteDataSource, locationDataSource)
    }

    @Test
    fun given_lat_and_long_getCurrentWeather_return_success() = runTest {
        success()
        val result = SUT.getCurrentWeatherData(30.99, 44.99)
        assert(result is Result.Success)
    }

    @Test
    fun given_lat_and_long_getCurrentWeather_return_fail() = runTest {
        fail()
        val result = SUT.getCurrentWeatherData(30.99, 44.99)
        assert(result is Result.Error)
    }

    @Test
    fun given_weatherDto_convert_to_weather_model() {
        val result = fakeWeatherDto.toWeatherDomainModel()
        assert(result == fakeWeatherModel)
    }

    @Test
    fun given_lat_and_long_getForecast_return_success() = runTest {
        successForecast()
        val result = SUT.getForecastData(30.99, 44.99)
        assert(result is Result.Success)
    }

    @Test
    fun given_lat_and_long_getForecast_return_fail() = runTest {
        failForecast()
        val result = SUT.getForecastData(30.99, 44.99)
        assert(result is Result.Error)
    }



    private suspend fun success() {
        whenever(remoteDataSource.getCurrentWeather(any(), any())).thenReturn(
            Result.Success(fakeWeatherDto)
        )
    }

    private suspend fun fail() {
        whenever(remoteDataSource.getCurrentWeather(any(), any())).thenReturn(
            Result.Error(DataError.Remote.SERVER)
        )
    }

    private suspend fun successForecast() {
        whenever(remoteDataSource.getForecast(any(), any())).thenReturn(
            Result.Success(fakeForecastDto)
        )
    }

    private suspend fun failForecast() {
        whenever(remoteDataSource.getForecast(any(), any())).thenReturn(
            Result.Error(DataError.Remote.SERVER)
        )

    }
}