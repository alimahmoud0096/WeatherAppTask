package com.alihafez.weatherapp

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alihafez.core.data.local.LocationData
import com.alihafez.core.data.local.LocationDataSource
import com.alihafez.core.data.local.settings.SettingsDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * created by ِAli Mahmoud Abdelhafez on 17/4/25
 **/
@HiltViewModel
class MainViewModel @Inject constructor(
    private val locationDataStore: LocationDataSource,
    settingsDataSource: SettingsDataSource
) :
    ViewModel() {

    val state = mutableStateOf(MainContract.MainUiState())
    val darkThemeFlow =
        settingsDataSource.getTheme().stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(500), false
        )

    fun setEvent(event: MainContract.MainEvent) {
        when (event) {
            is MainContract.MainEvent.CheckLocationSettings -> {
                state.value = state.value.copy(isLocationSettingEnabled = event.isEnabled)
            }

            is MainContract.MainEvent.GrantPermission -> {
                state.value = state.value.copy(isPermissionGranted = event.isGranted)
            }

            is MainContract.MainEvent.SaveLocation -> {
                state.value =
                    state.value.copy(userLocation = LocationData(event.latitude, event.longitude))
                saveLocation(event.latitude, event.longitude)
            }
        }
    }

    private fun saveLocation(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            locationDataStore.saveLocation(latitude, longitude)
        }
    }
}
