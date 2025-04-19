package com.alihafez.core.di

import com.alihafez.core.data.local.LocationDataSource
import com.alihafez.core.data.local.LocationDataStore
import com.alihafez.core.data.local.settings.SettingsDataSource
import com.alihafez.core.data.local.settings.SettingsDataStore
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * created by ِAli Mahmoud Abdelhafez on 17/4/25
 **/
@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    fun provideLocationDataStore(locationDataStore: LocationDataStore): LocationDataSource

    @Binds
    fun provideSettingsDataStore(settingsDataStore: SettingsDataStore): SettingsDataSource
}