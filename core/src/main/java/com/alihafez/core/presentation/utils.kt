package com.alihafez.core.presentation

import android.annotation.SuppressLint
import android.app.Activity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationSettingsResponse
import com.google.android.gms.location.LocationSettingsStatusCodes
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.Task
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import com.alihafez.weatherformat.iconIdForWeatherCondition


/**
 * created by ِAli Mahmoud Abdelhafez on 17/4/25
 **/

fun getWeatherIconBasedOnId(weatherId: Int): Int {
    return iconIdForWeatherCondition(weatherId)
}


fun createLocationRequest(
    activity: Activity,
    locationRequestLauncher: ActivityResultLauncher<IntentSenderRequest>,
    onLocationRequestSuccessful: () -> Unit
) {
    val locationRequest = LocationRequest.Builder(30_000L)
        .setPriority(Priority.PRIORITY_HIGH_ACCURACY) // PRIORITY_BALANCED_POWER_ACCURACY
        .build()

    val locationSettingsRequest = LocationSettingsRequest.Builder()
        .addLocationRequest(locationRequest)

    val task: Task<LocationSettingsResponse> =
        LocationServices.getSettingsClient(activity)
            .checkLocationSettings(locationSettingsRequest.build())

    task.addOnCompleteListener { response ->
        try {
            val result = response.getResult(ApiException::class.java)
            val hasLocationAccess = result.locationSettingsStates?.isLocationUsable == true
            if (hasLocationAccess) {
                onLocationRequestSuccessful()
            }
        } catch (exception: ApiException) {
            when (exception.statusCode) {
                LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> {
                    val resolvable = exception as ResolvableApiException
                    val intentSender =
                        IntentSenderRequest.Builder(resolvable.resolution).build()
                    locationRequestLauncher.launch(intentSender)
                }
                LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {
                    // Do nothing, location settings can't be changed
                }
            }
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

fun getWeatherIconForPreview() = com.alihafez.weatherformat.R.drawable.ic_wind