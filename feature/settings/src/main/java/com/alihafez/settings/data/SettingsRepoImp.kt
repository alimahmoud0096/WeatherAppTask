package com.alihafez.settings.data

import com.alihafez.core.data.local.settings.SettingsDataSource
import com.alihafez.settings.domain.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * created by ِAli Mahmoud Abdelhafez on 18/4/25
 **/
class SettingsRepoImp @Inject constructor(private val settingsDataSource: SettingsDataSource):SettingsRepository {
    override suspend fun changeTheme(isDark: Boolean) {
        settingsDataSource.changeTheme(isDark)
    }

    override fun getTheme(): Flow<Boolean> {
       return settingsDataSource.getTheme()
    }
}