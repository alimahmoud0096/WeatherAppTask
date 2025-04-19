package com.alihafez.core.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * created by ِAli Mahmoud Abdelhafez on 17/4/25
 **/
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "location")

class LocationDataStore @Inject constructor(@ApplicationContext private val context: Context) :
    LocationDataSource {

    // Keys for latitude and longitude
    companion object {
        val LATITUDE_KEY = doublePreferencesKey("latitude")
        val LONGITUDE_KEY = doublePreferencesKey("longitude")
    }

    // Save location data
    override suspend fun saveLocation(latitude: Double, longitude: Double) {
        context.dataStore.edit { preferences ->
            preferences[LATITUDE_KEY] = latitude
            preferences[LONGITUDE_KEY] = longitude
        }
    }

    override fun getLocation(): Flow<LocationData> {
        return locationFlow
    }

    // Get location data as Flow
    private val locationFlow: Flow<LocationData> = context.dataStore.data
        .map { preferences ->
            val latitude = preferences[LATITUDE_KEY] ?: 0.0
            val longitude = preferences[LONGITUDE_KEY] ?: 0.0
            LocationData(latitude, longitude)
        }
}