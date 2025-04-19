package com.alihafez.core.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

/**
 * created by ِAli Mahmoud Abdelhafez on 18/4/25
 **/
@Composable
fun ScreenContainer(
    modifier: Modifier = Modifier,
    screen: @Composable (() -> Unit),
    isLoading: Boolean,
    errorDialogText: UiText? = null,
    onDialogButtonClick: () -> Unit = {}
) {
    var showDialog by remember(errorDialogText) { mutableStateOf(errorDialogText != null) }

    Box(modifier = modifier) {
        screen()
        CircularLoading(isLoading = isLoading)
        AnimatedVisibility(visible = showDialog) {
            if (errorDialogText != null) {
                ErrorDialog(errorMessage = errorDialogText) {
                    showDialog = false // ✅ dismiss the dialog
                    onDialogButtonClick()
                }
            }
        }
    }
}

