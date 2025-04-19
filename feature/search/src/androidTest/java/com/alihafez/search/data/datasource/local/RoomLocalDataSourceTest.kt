package com.alihafez.search.data.datasource.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.alihafez.core.data.local.database.WeatherDao
import com.alihafez.core.data.local.database.WeatherDatabase
import com.alihafez.search.data.datasource.toWeatherEntity
import com.alihafez.search.fake.fakeWeatherModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/**
 * created by ِAli Mahmoud Abdelhafez on 18/4/25
 */
@RunWith(AndroidJUnit4::class)
class RoomLocalDataSourceTest {

    private lateinit var userDao: WeatherDao
    private lateinit var db: WeatherDatabase

    private lateinit var SUT: RoomLocalDataSource

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, WeatherDatabase::class.java
        ).build()
        userDao = db.weatherDao()

        SUT = RoomLocalDataSource(userDao)
    }

    @Test
    fun save_Location_return_saved_item() = runTest {
        SUT.saveLocationToDatabase(fakeWeatherModel.toWeatherEntity())

        val result = SUT.getCashedList().first() // Get the first emitted value

        assertEquals(result[0].cityName, fakeWeatherModel.name)

    }



    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }
}