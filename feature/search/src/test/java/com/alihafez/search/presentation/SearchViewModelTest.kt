package com.alihafez.search.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.alihafez.search.domain.repo.SearchRepo
import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import com.alihafez.core.domain.*
import com.alihafez.search.MainDispatcherRule
import com.alihafez.search.fake.fakeWeatherModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

/**
 * created by ِAli Mahmoud Abdelhafez on 18/4/25
 */
@RunWith(MockitoJUnitRunner.Silent::class)
class SearchViewModelTest {

    @Mock
    private lateinit var searchRepo: SearchRepo
    private lateinit var SUT: SearchViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainDispatcherRule()

    @Before
    fun setup() {
        getEmptyCachedList()
        SUT = SearchViewModel(searchRepo)
    }

    @Test
    fun get_city_by_name_return_success() = runTest {
        getWeatherCity()
        SUT.onCityNameChange("San Francisco")
        SUT.onSearchIconClick()
        SUT.uiState.test {
            val firstItem = awaitItem()
            assertEquals(firstItem, fakeWeatherModel.asSearchUiState())
            cancelAndConsumeRemainingEvents()
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun save_Location_updated_in_cashed_list() = runTest(UnconfinedTestDispatcher()) {
        getWeatherCity()
        saveLocation()

        SUT.onCityNameChange("San Francisco")
        SUT.onSearchIconClick()
        SUT.onSavedLocationClick()


        SUT.uiState.test {
            val result = awaitItem()

            assertEquals(result.savedLocations.size, 1)
        }
    }

    private suspend fun getWeatherCity() {
        whenever(searchRepo.getWeatherByCityName(any())).thenReturn(
            Result.Success(
                fakeWeatherModel
            )
        )
    }

    private fun getEmptyCachedList() {
        whenever(searchRepo.getCashedList()).thenReturn(flowOf(emptyList()))
    }

    private suspend fun saveLocation() {
        whenever(searchRepo.saveLocationToDatabase(any())).thenReturn(Unit)
        whenever(searchRepo.getCashedList()).thenReturn(flowOf(listOf(fakeWeatherModel)))
    }
}