package com.alihafez.settings.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alihafez.core.presentation.preview.PreviewLightDarkMode

/**
 * created by ِAli Mahmoud Abdelhafez on 18/4/25
 **/
@Composable
fun SettingsRoute(modifier: Modifier = Modifier, viewModel: SettingsViewModel = hiltViewModel()) {

    val isDarkMode by viewModel.darkThemeFlow.collectAsStateWithLifecycle()
    SettingsScreen(
        modifier = modifier,
        isDarkMode = isDarkMode,
        onChangeTheme = viewModel::changeThem
    )
}

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    isDarkMode: Boolean = false,
    onChangeTheme: (Boolean) -> Unit
) {
    Column(modifier = modifier.fillMaxSize()) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Dark Mode", color = MaterialTheme.colorScheme.surfaceTint)
            Switch(checked = isDarkMode, onCheckedChange = onChangeTheme)
        }
    }
}

@PreviewLightDarkMode
@Composable
private fun SettingsScreenPreview() {
    SettingsScreen(isDarkMode = true, onChangeTheme = {})
}