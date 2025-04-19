package com.alihafez.search.data.repoImpl

import com.alihafez.core.domain.Result
import com.alihafez.search.data.datasource.local.LocalDataSource
import com.alihafez.search.data.datasource.remote.RemoteDataSource
import com.alihafez.search.data.datasource.toWeatherEntity
import com.alihafez.search.fake.fakeWeatherDto
import com.alihafez.search.fake.fakeWeatherModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
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
class SearchRepoImplTest {
    @Mock
    private lateinit var remoteDataSource: RemoteDataSource

    @Mock
    private lateinit var localDataSource: LocalDataSource

    private lateinit var SUT: SearchRepoImpl

    @Before
    fun setup() {
        SUT = SearchRepoImpl(remoteDataSource, localDataSource)
    }

    @Test
    fun search_CityBy_Name_return_Success() = runTest {
        searchCity()
        val result = SUT.getWeatherByCityName("San Francisco")
        assertTrue(result is Result.Success)
        assertEquals((result as Result.Success).data.name, "San Francisco")

    }

    @Test
    fun save_Location_return_Success() = runTest {
        saveLocation()
        val result = SUT.getCashedList().first()
        assertEquals(result.first().name, "San Francisco")
    }

    private suspend fun searchCity() {
        whenever(remoteDataSource.getWeatherByCityName(any())).thenReturn(
            Result.Success(
                fakeWeatherDto
            )
        )
    }

    private suspend fun saveLocation() {
        whenever(localDataSource.getCashedList()).thenReturn(
            flowOf(listOf(fakeWeatherModel.toWeatherEntity()))
        )
    }
}