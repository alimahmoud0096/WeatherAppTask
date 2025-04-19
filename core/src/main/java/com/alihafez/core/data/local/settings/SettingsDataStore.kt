package com.alihafez.core.data.local.settings

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * created by ِAli Mahmoud Abdelhafez on 18/4/25
 **/
val Context.settingsDataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingsDataStore @Inject constructor(@ApplicationContext private val context: Context) :
    SettingsDataSource {
    // Keys for latitude and longitude
    companion object {
        val DARK_THEME = booleanPreferencesKey("dark_theme")
    }

    override suspend fun changeTheme(isDark: Boolean) {
        context.settingsDataStore.edit { preferences ->
            preferences[DARK_THEME] = isDark
        }
    }

    override fun getTheme(): Flow<Boolean> {
        return darkThemeFlow
    }

    // Get location data as Flow
    private val darkThemeFlow: Flow<Boolean> = context.settingsDataStore.data
        .map { preferences ->
            preferences[DARK_THEME] ?: false

        }

}