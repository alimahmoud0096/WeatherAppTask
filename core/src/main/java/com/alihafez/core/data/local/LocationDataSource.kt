package com.alihafez.core.data.local

import kotlinx.coroutines.flow.Flow

/**
 * created by ِAli Mahmoud Abdelhafez on 17/4/25
 **/
interface LocationDataSource {
    suspend fun saveLocation(latitude: Double, longitude: Double)
    fun getLocation(): Flow<LocationData>
}