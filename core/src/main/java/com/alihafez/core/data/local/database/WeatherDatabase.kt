package com.alihafez.core.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * created by ِAli Mahmoud Abdelhafez on 18/4/25
 **/
@Database(entities = [WeatherEntity::class], version = 1, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}