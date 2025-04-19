package com.alihafez.weatherapp.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alihafez.core.presentation.preview.PreviewLightDarkMode
import com.alihafez.weatherapp.R

/**
 * created by ِAli Mahmoud Abdelhafez on 17/4/25
 **/
@Composable
fun InfoScreens(@StringRes message: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.weight(0.5f))
        Text(
            text = stringResource(message),
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.weight(0.5f))
    }
}

@PreviewLightDarkMode
@Composable
fun InfoScreensPreview() {
    InfoScreens(message = R.string.location_no_permission_screen_description)
}
