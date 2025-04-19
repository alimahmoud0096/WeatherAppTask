package com.alihafez.core.di

import android.content.Context
import androidx.room.Room
import com.alihafez.core.data.local.database.WeatherDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * created by ِAli Mahmoud Abdelhafez on 18/4/25
 **/
@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext applicationContext: Context): WeatherDatabase {

        return Room.databaseBuilder(
            applicationContext,
            WeatherDatabase::class.java, "Weather"
        ).fallbackToDestructiveMigration()

            .build()
    }

    @Provides
    @Singleton
    fun provideDao(database: WeatherDatabase) = database.weatherDao()
}