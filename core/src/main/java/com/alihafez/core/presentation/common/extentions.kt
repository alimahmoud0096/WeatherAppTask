package com.alihafez.core.presentation.common

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.core.content.ContextCompat
import com.alihafez.core.presentation.PermissionRationaleDialog
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

/**
 * created by ِAli Mahmoud Abdelhafez on 18/4/25
 **/
@Composable
fun Activity.OnPermissionDenied(
    activityPermissionResult: ActivityResultLauncher<String>,
) {
    val showWeatherUI = remember { mutableStateOf(false) }
    if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION)) {
        val isDialogShown = remember { mutableStateOf(true) }
        if (isDialogShown.value) {
            PermissionRationaleDialog(
                isDialogShown,
                activityPermissionResult,
                showWeatherUI
            )
        }
    } else {
        activityPermissionResult.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
    }
}

@Composable
fun Context.CheckForPermissions(
    onPermissionGranted: @Composable () -> Unit,
    onPermissionDenied: @Composable () -> Unit
) {
    when (
        ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    ) {
        PackageManager.PERMISSION_GRANTED -> {
            onPermissionGranted()
        }
        PackageManager.PERMISSION_DENIED -> {
            onPermissionDenied()
        }
    }
}

@SuppressLint("NewApi")
fun String.formatDate(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    val dateTime = LocalDateTime.parse(this, formatter)
    return dateTime.toLocalDate().toString()
}

fun String.formatTime(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val outputFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

    val date = inputFormat.parse(this) // Parse to Date object
    return outputFormat.format(date!!) // Format to HH:mm
}
