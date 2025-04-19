package com.alihafez.core.data.local.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * created by ِAli Mahmoud Abdelhafez on 18/4/25
 **/
@Entity(tableName = "weather")
data class WeatherEntity(
    @ColumnInfo(name = "city_name") val cityName: String = "",
    @ColumnInfo("weather_name") val weatherName: String = "",
    @ColumnInfo("visibility") val visibility: Int = 0,
    @ColumnInfo("humidity") val humidity: Int = 0,
    @ColumnInfo("pressure") val pressure: Int = 0,
    @ColumnInfo("temp_max") val tempMax: Double = 0.0,
    @ColumnInfo("temp_min") val tempMin: Double = 0.0,
    @ColumnInfo("temp") val temp: Double = 0.0,
    @ColumnInfo("weather_id") val weatherId: Int = 0,
    @ColumnInfo("windSpeed") val windSpeed: Double = 0.0,
    @PrimaryKey(autoGenerate = true) val _id: Int = 0
)
