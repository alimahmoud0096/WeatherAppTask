package com.alihafez.formatweather


import android.annotation.SuppressLint
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * created by Ali Mahmoud Abdelhafez on 17/4/25
 **/
fun iconIdForWeatherCondition(weatherId: Int): Int {


    when (weatherId) {
        in 200..232 -> {
            return R.drawable.storm
        }
        in 300..321 -> {
            return R.drawable.rain
        }
        in 500..504 -> {
            return R.drawable.rain
        }
        511 -> {
            return R.drawable.snowy
        }
        in 520..531 -> {
            return R.drawable.rainy
        }
        in 600..622 -> {
            return R.drawable.snowy
        }
        in 701..761 -> {
            return R.drawable.windy
        }
        762, 771, 781 -> {
            return R.drawable.storm
        }
        800 -> {
            return R.drawable.sunny
        }
        801 -> {
            return R.drawable.cloudy_sunny
        }
        in 802..804 -> {
            return R.drawable.cloudy
        }
        in 900..906 -> {
            return R.drawable.storm
        }
        in 958..962 -> {
            return R.drawable.storm
        }
        in 951..957 -> {
            return R.drawable.sunny
        }
        else -> {
            return R.drawable.storm
        }
    }
}


@SuppressLint("NewApi")
fun getCurrentDayFormatted(): String {
    // Get the current date
    val currentDate = LocalDate.now()

    // Define the desired format
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    println(currentDate.format(formatter))
    // Format the current date
    return currentDate.format(formatter)
}