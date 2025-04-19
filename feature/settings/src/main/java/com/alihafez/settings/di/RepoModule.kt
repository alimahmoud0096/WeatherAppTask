package com.alihafez.settings.di

import com.alihafez.settings.data.SettingsRepoImp
import com.alihafez.settings.domain.SettingsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * created by ِAli Mahmoud Abdelhafez on 18/4/25
 **/
@Module
@InstallIn(SingletonComponent::class)
interface RepoModule {
    @Binds
    fun provideSettingsRepository(settingsRepoImp: SettingsRepoImp): SettingsRepository
}