package com.alihafez.core.data.local.settings

import kotlinx.coroutines.flow.Flow

/**
 * created by ِAli Mahmoud Abdelhafez on 18/4/25
 **/
interface SettingsDataSource {
    suspend fun changeTheme(isDark: Boolean)
    fun getTheme(): Flow<Boolean>

}