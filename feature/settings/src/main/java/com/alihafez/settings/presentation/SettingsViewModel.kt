package com.alihafez.settings.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alihafez.settings.domain.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * created by ِAli Mahmoud Abdelhafez on 18/4/25
 **/
@HiltViewModel
class SettingsViewModel @Inject constructor(private val settingsRepository: SettingsRepository) :
    ViewModel() {

    val darkThemeFlow =
        settingsRepository.getTheme().stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(500), false
        )

    fun changeThem(isDark: Boolean) {
        viewModelScope.launch {
            settingsRepository.changeTheme(isDark)
        }
    }
}