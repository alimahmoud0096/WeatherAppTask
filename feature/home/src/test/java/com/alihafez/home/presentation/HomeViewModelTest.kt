package com.alihafez.home.presentation

import app.cash.turbine.test
import com.alihafez.core.R
import com.alihafez.core.data.local.LocationData
import com.alihafez.home.domain.repository.HomeRepository
import com.alihafez.home.domain.usecase.GetHourlyNextDaysUseCase
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import com.alihafez.core.domain.*
import com.alihafez.core.presentation.UiText
import com.alihafez.core.presentation.common.WeatherUi
import com.alihafez.core.presentation.toUiText
import com.alihafez.home.MainDispatcherRule
import com.alihafez.home.data.fake.*
import com.alihafez.home.data.toForecastDomainModel
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

/**
 * created by ِAli Mahmoud Abdelhafez on 18/4/25
 */
@RunWith(MockitoJUnitRunner.Silent::class)
class HomeViewModelTest {

    @Mock
    private lateinit var homeRepository: HomeRepository

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var getHourlyNextDaysUseCase: GetHourlyNextDaysUseCase
    private lateinit var SUT: HomeViewModel

    @Before
    fun setup() {
        getHourlyNextDaysUseCase = GetHourlyNextDaysUseCase(homeRepository)
        SUT = HomeViewModel(homeRepository, getHourlyNextDaysUseCase)
    }

    @Test
    fun given_lat_and_long_call_get_forecast_return_success_weather_and_forecast() = runTest {
        getLocation()
        getCurrentWeather()
        getForecast()

        SUT.currentWeatherStateFlow.test {
            val firstItem = awaitItem()
            assertFalse(firstItem.isLoading)

            assertEquals(
                fakeWeatherModel.asHomeUiState()
                    .copy(forecast = listOf(fakeForecastModelItem.asHomeForecastUi())), firstItem
            )
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun given_lat_and_long_call_get_forecast_return_fail_weather_and_success_forecast() = runTest {
        getLocation()
        failCurrentWeather()
        getForecast()

        SUT.currentWeatherStateFlow.test {
            val firstItem = awaitItem()
            assertEquals(
                firstItem.weatherUi,
                WeatherUi()
            )

            assertEquals(
                firstItem.forecast,
                listOf(fakeForecastModelItem.asHomeForecastUi())
            )

            cancelAndConsumeRemainingEvents()
        }
    }

    private fun getLocation() {
        whenever(homeRepository.getLocation()).thenReturn(
            flowOf(LocationData(30.99, 99.10))
        )
    }

    private suspend fun getCurrentWeather() {
        whenever(homeRepository.getCurrentWeatherData(any(), any())).thenReturn(
            Result.Success(fakeWeatherModel)
        )
    }

    private suspend fun failCurrentWeather() {
        whenever(homeRepository.getCurrentWeatherData(any(), any())).thenReturn(
            Result.Error(DataError.Remote.SERVER)
        )
    }

    private suspend fun getForecast() {
        whenever(homeRepository.getForecastData(any(), any())).thenReturn(
            Result.Success(fakeForecastDto.toForecastDomainModel())
        )

    }

}