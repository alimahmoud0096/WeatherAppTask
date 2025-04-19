package com.alihafez.settings.domain

import kotlinx.coroutines.flow.Flow

/**
 * created by ِAli Mahmoud Abdelhafez on 18/4/25
 **/
interface SettingsRepository {
    suspend fun changeTheme(isDark: Boolean)
    fun getTheme(): Flow<Boolean>
}